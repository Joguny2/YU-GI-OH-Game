package eg.edu.guc.yugioh.board.player;
import java.io.IOException;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;          //removed unused import (mode)
import eg.edu.guc.yugioh.cards.spells.Raigeki;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.exceptions.NoMonsterSpaceException;
import eg.edu.guc.yugioh.exceptions.NoSpellSpaceException;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.gui.CardType;
import eg.edu.guc.yugioh.gui.MyButton;
import eg.edu.guc.yugioh.gui.PlayerField;
import eg.edu.guc.yugioh.listeners.MonsterCardAL;
import eg.edu.guc.yugioh.listeners.SpellCardAL;

import javax.swing.JOptionPane;
public class Field 
{
	private Phase phase;
	private ArrayList<MonsterCard> monstersArea;
	private ArrayList<SpellCard> spellArea;
	private ArrayList<Card> hand;
	private ArrayList<Card> graveyard;
	private PlayerField pField;
	private Deck deck;
	public Field() throws IOException , UnexpectedFormatException 
	{
		this.phase=Phase.MAIN1;
		monstersArea = new ArrayList<MonsterCard>(); 
		spellArea = new ArrayList<SpellCard>();
		hand = new ArrayList<Card>();
		graveyard = new ArrayList<Card>();
		this.deck=new Deck();
	}
	public void addMonsterToField(MonsterCard monster, Mode m, boolean isHidden){
		if(monster.getLocation()==Location.HAND){
		if(monstersArea.size()<5)
		{	
			hand.remove(monster);
			monstersArea.add(monster);       
		    monster.setMode(m);
		    monster.setHidden(isHidden);
		    monster.setLocation(Location.FIELD);
		    
		}
		else
			throw new NoMonsterSpaceException();
		}
	
		
	}
	public void addMonsterToField(MonsterCard monster, Mode mode, ArrayList<MonsterCard> sacrifices)
	{
		if(monster.getLocation()==Location.HAND){
		if(monster.getLevel()>=1 && monster.getLevel()<=4){
			if(sacrifices.size()!=0)
				return;
		}
		if(monster.getLevel()==5 || monster.getLevel()==6){
			if(sacrifices.size()!=1)
				return;
		}
	 if(monster.getLevel()==7 || monster.getLevel()==8){
			if(sacrifices.size()!=2)
				return;
				
		}
		
		removeMonsterToGraveyard(sacrifices);
		
		addMonsterToField(monster,mode,false);
		}
	}
	
	
	public void removeMonsterToGraveyard(MonsterCard monster)
	{
			this.getpField().getMnstrPanel().removeButton(monster,this);
			this.getpField().getMnstrPanel().revalidate();
			this.getpField().getMnstrPanel().repaint();
			
		monstersArea.remove(monster); 
		graveyard.add(monster);
		
		monster.setLocation(Location.GRAVEYARD);
		Card.getBoard().updateGraveyard();
	}
	public void removeMonsterToGraveyard(ArrayList<MonsterCard> monsters)
	{   
		if (monsters!=monstersArea)
		{
			for (int i=0;i<monsters.size();i++)
			{
				removeMonsterToGraveyard(monsters.get(i));
				//monsters.remove(0);
			}
			
			return;
		}
		
		while(!monsters.isEmpty()){
			removeMonsterToGraveyard((monsters.get(0)));
		}
		Card.getBoard().updateGraveyard();
	}
	
	public void addSpellToField(SpellCard action,MonsterCard monster, boolean hidden){
		if(spellArea.size()<5 && action.getLocation()==Location.HAND){
			
			action.setLocation(Location.FIELD);
			spellArea.add(action);
			//pField.getSplPanel().addButton(m);
			if(!hidden)
				activateSetSpell(action,monster);      
		}
	}
	public void activateSetSpell(SpellCard action, MonsterCard monster)
	{
		if (action.getLocation()!=Location.FIELD)
			return;
		action.setHidden(false);
		
		action.action(monster);
		removeSpellToGraveyard(action);
		
	}
	public void removeSpellToGraveyard(SpellCard spell)
	{
		if(spell.getLocation()!=Location.FIELD)
			return;
		this.getpField().getSplPanel().removeButton(spell,this);
		spellArea.remove(spell);                             //new method
		graveyard.add(spell);
		spell.setLocation(Location.GRAVEYARD);
		Card.getBoard().updateGraveyard();
	
	}
	public void removeSpellToGraveyard(ArrayList<SpellCard> spells)
	{
		if (spellArea==spells)
		{	
			while (!spells.isEmpty())
				removeSpellToGraveyard(spells.get(0));
			return;
		}
		
		for (int i=0;i<spells.size();i++)
		{
			removeSpellToGraveyard(spells.get(i));           
		} 
	}
	public void addCardToHand()
	{
		if (deck.getDeck().size()==0)
		{
			Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
			return;
		}
		Card c=deck.drawOneCard();
		ArrayList<Card> plyrhand = this.getHand();
		//System.out.println(this.getpField());
		MyButton m = new MyButton(c.getName());
		m.setOpaque(false);
		ImageIcon myCardImage = new ImageIcon(c.getName()+".png");
	    m.setIcon(myCardImage);
	   m.revalidate();
	    m.repaint();
		if (c instanceof SpellCard)
		{
			m.setCtype(CardType.SPELL);
			m.addActionListener(new SpellCardAL());
			m.setToolTipText(c.getName());
		}
		else
		{
			m.setCtype(CardType.MONSTER);
			MonsterCard c1 = (MonsterCard) c;
			m.setToolTipText(c1.getInfo());
			m.addActionListener(new MonsterCardAL());
		}
		m.setBounds(26*(plyrhand.size()), 0, 100, 120);

		m.setLoc(Location.HAND);
		this.getpField().getHPanel().getHandButtons().add(m);
		this.getpField().getHPanel().add(m);
		//JOptionPane.showConfirmDialog(null,"hi");
		this.getpField().getHPanel().revalidate();
		this.getpField().getHPanel().repaint();
		this.getpField().revalidate();
		this.getpField().repaint();
		this.getpField().getDeckButton().setText("Deck "+this.getDeck().getDeck().size());
		this.getpField().getHPanel().formulate();
		if(c!=null){
		hand.add(c);
		
		c.setLocation(Location.HAND);
		}
	}
	public PlayerField getpField() {
		return pField;
	}
	public void setpField(PlayerField pField) {
		this.pField = pField;
	}
	public void addNCardsToHand(int n){
		for(int i=0;i<n;i++){
			addCardToHand();
		}
	}
	
	public void removeHandToGraveyard(Card card)
	{      
		int index=hand.indexOf(card);
		hand.remove(card);
		MyButton m = pField.getHPanel().getHandButtons().get(index);
		pField.getHPanel().remove(m);
		pField.getHPanel().getHandButtons().remove(m);
		graveyard.add(card);
		card.setLocation(Location.GRAVEYARD);
		Card.getBoard().updateGraveyard();
	}
	public void removeHandToGraveyard(ArrayList<Card> cards)
	{
		if (cards==hand)
		{
			while (!cards.isEmpty())
			{      
				removeHandToGraveyard(cards.get(0));          
			}
			return;
		}
		for (int i=0;i<cards.size();i++)
			removeHandToGraveyard(cards.get(i));
	}
	
	
	public Phase getPhase() {
		return phase;
	}
	public void setPhase(Phase phase) {
		this.phase = phase;
	}
	public ArrayList<MonsterCard> getMonstersArea() {
		return monstersArea;
	}
	public ArrayList<SpellCard> getSpellArea() {
		return spellArea;
	}
	public ArrayList<Card> getHand() {
		return hand;
	}
	public ArrayList<Card> getGraveyard() {
		return graveyard;
	}
	public Deck getDeck() {
		return deck;
	}

	
	
}
