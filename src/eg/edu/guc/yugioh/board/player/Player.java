package eg.edu.guc.yugioh.board.player;

import java.io.IOException;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.exceptions.NoSpellSpaceException;
import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.DefenseMonsterAttackException;
import eg.edu.guc.yugioh.exceptions.MonsterMultipleAttackException;
import eg.edu.guc.yugioh.exceptions.MultipleMonsterAdditionException;
import eg.edu.guc.yugioh.exceptions.NoMonsterSpaceException;
import eg.edu.guc.yugioh.exceptions.NoSpellSpaceException;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.exceptions.WrongPhaseException;
import eg.edu.guc.yugioh.gui.MyButton;
import eg.edu.guc.yugioh.gui.PlayerField;

public class Player implements Duelist
{
	private String name;
	private int lifePoints;
	private Field field;
	private boolean summ=false;
	public boolean setSpell(SpellCard spell)
	{
		if(Card.getBoard().getWinner()!=null)
			return false;
		if(Card.getBoard().getActivePlayer().getField().getPhase()==Phase.BATTLE)
		{
			JOptionPane.showMessageDialog(null,"You can't Set Spell in Battle Phase");
			return false;
		
		}
		if(Card.getBoard().getActivePlayer()!=this)
			return false;
		if(Card.getBoard().getActivePlayer().getField().getSpellArea().size()>=5)
		{
			JOptionPane.showMessageDialog(null,"There's no empty spell field");
			return false;
		}
		
		Player active = Card.getBoard().getActivePlayer();
		MyButton spl = Card.getBoard().getSpell();
		active.getField().getHand().remove(spell);
		active.getField().getpField().getHPanel().remove(spl);
		active.getField().getpField().getHPanel().getHandButtons().remove(spl);
		active.getField().getpField().getHPanel().revalidate();
		active.getField().getpField().getHPanel().repaint();
		Card.getBoard().getActivePlayer().getField().addSpellToField(spell,null,true);
		active.getField().getpField().getSplPanel().addButton(spl);
		active.getField().getpField().getHPanel().formulate();
		//spell.setLocation(Location.FIELD);
		return true;
	}
	public boolean activateSpell(SpellCard spell, MonsterCard monster)
	{
		if(Card.getBoard().getWinner()!=null)
			return false;
		if(Card.getBoard().getActivePlayer().getField().getPhase()==Phase.BATTLE)
		{
			JOptionPane.showMessageDialog(null,"You can't Activate Spell in Battle Phase");
			return false;
		}
		if(Card.getBoard().getActivePlayer()!=this)
			return false;
		if(spell.getLocation()==Location.HAND)
		{
			if((Card.getBoard().getActivePlayer().getField().getSpellArea().size()>=5))
			{
				JOptionPane.showMessageDialog(null,"There's no empty spell field");
				return false;
			}
				
			Card.getBoard().getActivePlayer().getField().addSpellToField(spell, monster, false);
			
			return true;
		}
		if(spell.getLocation()==Location.FIELD)
		{
			//JOptionPane.showInputDialog("7amada");
			Card.getBoard().getActivePlayer().getField().activateSetSpell(spell, monster);
			return true;
		}
		return false;
	}
	public boolean declareAttack(MonsterCard activeMonster, MonsterCard opponentMonster)
	{
		if(Card.getBoard().getWinner()!=null)
			return false;
		if(Card.getBoard().getActivePlayer().getField().getPhase()!=Phase.BATTLE)
		{
			JOptionPane.showMessageDialog(null, "You can't attack in this phase");
			return false;
			//throw new WrongPhaseException();
		}
		if(Card.getBoard().getActivePlayer()!=this)
			return false;
		if(activeMonster.getLocation()!=Location.FIELD)
			return false;
		if(opponentMonster.getLocation()!=Location.FIELD)
			return false;
		if(activeMonster.getMode()!=Mode.ATTACK)
		{
			JOptionPane.showMessageDialog(null, "You can't attack in Defense Mode");
			//throw new DefenseMonsterAttackException();
			return false;
		}
		if(activeMonster.getFlag()==true)
		{
			JOptionPane.showMessageDialog(null, "You didn't attack with the same monster twice in one turn");
			//throw new MonsterMultipleAttackException();
			return false;
		}
		//JOptionPane.showMessageDialog(null, "Mesh yala ne5alas ba2a wala eh :D");
		//return;
		activeMonster.action(opponentMonster);
		return true;
	}
	public boolean declareAttack(MonsterCard activeMonster)
	{
		if(Card.getBoard().getWinner()!=null)
			return false;
		if(Card.getBoard().getActivePlayer().getField().getPhase()!=Phase.BATTLE)
		{
			JOptionPane.showMessageDialog(null, "You can not attack in this phase");
			//throw new WrongPhaseException();
			return false;
		}
		if(Card.getBoard().getActivePlayer()!=this)
			return false;
		if(activeMonster.getLocation()!=Location.FIELD)
			return false;
		if(activeMonster.getMode()!=Mode.ATTACK)
		{
			JOptionPane.showMessageDialog(null, "You can not attack in defense mode");
			//throw new DefenseMonsterAttackException();
			return false;
		}
		if(activeMonster.getFlag()==true)
		{
			//throw new MonsterMultipleAttackException();
			JOptionPane.showMessageDialog(null, "You can not attack with the same monster twice");
			return false;
		}
		activeMonster.action();
		return true;
	}
	public boolean switchMonsterMode(MonsterCard monster)
	{
		if(Card.getBoard().getWinner()!=null)
			return false;
		if(Card.getBoard().getActivePlayer()!=this)
			return false;
		if(Card.getBoard().getActivePlayer().getField().getPhase()==Phase.BATTLE){
			JOptionPane.showMessageDialog(null, "Can't switch in battle phase");
			//throw new WrongPhaseException();
			return false;
		}
		if(monster.getLocation()!=Location.FIELD)
			return false;
		int index=Card.getBoard().getActivePlayer().getField().getMonstersArea().indexOf(monster);
		MyButton b=Card.getBoard().getActivePlayer().getField().getpField().getMnstrPanel().getMonstersButtons().get(index);
		
		if(monster.getMode()==Mode.ATTACK){
			//b.setText("YUGIOH");
			b.setIcon(new ImageIcon("Card Back.png"));
			b.setToolTipText(null);
			monster.setMode(Mode.DEFENSE);
		}
		else if(monster.getMode()==Mode.DEFENSE ){
			//b.setText(monster.getName());
			b.setIcon(new ImageIcon(monster.getName()+".png"));
			b.setToolTipText(monster.getInfo());
			monster.setMode(Mode.ATTACK);
		}
		//Card.getBoard().getActivePlayer().getField().setPhase(Phase.BATTLE);
		return true;
		
	}
	public Player (String name) throws IOException , UnexpectedFormatException 
	{
		field = new Field();
		this.name=name;
		lifePoints=8000;
		//field = new Field();
	}
	
	public int getLifePoints() 
	{
		return lifePoints;
	}
	public void setLifePoints(int lifepoints) 
	{
		this.lifePoints = lifepoints;
	}
	public String getName() 
	{
		return name;
	}
	public Field getField() 
	{
		return field;
	}
	
	public boolean summonMonster(MonsterCard monster)
	{
		if(Card.getBoard().getWinner()!=null)
			return false;
		if(Card.getBoard().getActivePlayer().getField().getPhase()==Phase.BATTLE)
		{
			JOptionPane.showMessageDialog(null,"You can't Summon Monster in Battle Phase");
			return false;
		}
		if(Card.getBoard().getActivePlayer()!=this)
			return false;
		if(Card.getBoard().getActivePlayer().getField().getMonstersArea().size()>=5)
		{
			JOptionPane.showMessageDialog(null,"There is no empty monster field");
			return false;
		}
		if(summ==true)
		{
			JOptionPane.showMessageDialog(null,"You can't add monster twice in one turn");
			return false;
		}
		summ=true;
		Player active = Card.getBoard().getActivePlayer();
		MyButton mnstr = Card.getBoard().getMonster();
		//mnstr.setToolTipText(mnstr.getToolTipText()+"<br>"+"ATTACK");
		active.getField().getHand().remove(monster);
		active.getField().getpField().getHPanel().remove(mnstr);
		active.getField().getpField().getHPanel().getHandButtons().remove(mnstr);
		active.getField().getpField().getHPanel().revalidate();
		active.getField().getpField().getHPanel().repaint();
		Card.getBoard().getActivePlayer().getField().addMonsterToField(monster,Mode.ATTACK,false);
		active.getField().getpField().getMnstrPanel().addButton(mnstr);
		this.getField().getpField().getHPanel().formulate();
		//mnstr.setToolTipText(text);
		
		return true;
	}
	public boolean summonMonster(MonsterCard monster, ArrayList<MonsterCard> sacrifices)
	{
		//if(summ==true)
			//throw new MultipleMonsterAdditionException();
			//return false;
		summ=true;
		if(monster.getLevel()>=1 && monster.getLevel()<=4){
			if(sacrifices.size()!=0)
				return false;
		}
		if(monster.getLevel()==5 || monster.getLevel()==6){
			if(sacrifices.size()!=1)
				return false;
		}
	 if(monster.getLevel()==7 || monster.getLevel()==8){
			if(sacrifices.size()!=2)
				return false;
	 }
		if(Card.getBoard().getActivePlayer().getField().getPhase()==Phase.BATTLE)
		{
			JOptionPane.showMessageDialog(null, "You Can't summon monster in Battle Phase");
			//throw new WrongPhaseException();
			return false;
		}
		if(Card.getBoard().getActivePlayer()!=this)
			return false;
		if(Card.getBoard().getActivePlayer().getField().getMonstersArea().size()>=5)
		{
			JOptionPane.showMessageDialog(null, "There's No Empty Monster Space");
			//throw new NoMonsterSpaceException();
			return false;
		}
			//Card.getBoard().getActivePlayer().getField().addMonsterToField(monster,Mode.ATTACK,sacrifices);
			Player active = Card.getBoard().getActivePlayer();
			MyButton mnstr = Card.getBoard().getMonster();
			//mnstr.setToolTipText(mnstr.getToolTipText()+"<br>"+"ATTACK");
			active.getField().getHand().remove(monster);
			active.getField().getpField().getHPanel().remove(mnstr);
			active.getField().getpField().getHPanel().getHandButtons().remove(mnstr);
			active.getField().getpField().getHPanel().revalidate();
			active.getField().getpField().getHPanel().repaint();
			Card.getBoard().getActivePlayer().getField().addMonsterToField(monster,Mode.ATTACK,false);
			active.getField().getpField().getMnstrPanel().addButton(mnstr);
			this.getField().getpField().getHPanel().formulate();
			//mnstr.setToolTipText(text);
		return true;
	}
	public boolean setMonster(MonsterCard monster)
	{
		//Defence means hidden ? wala malhash 3laka
		if(Card.getBoard().getWinner()!=null)
			return false;
		if(Card.getBoard().getActivePlayer().getField().getPhase()==Phase.BATTLE){
			JOptionPane.showMessageDialog(null, "Can't set monster in BATTLE phase");
			//throw new WrongPhaseException();
			return false;
		}
		if(Card.getBoard().getActivePlayer()!=this)
			return false;
		if(Card.getBoard().getActivePlayer().getField().getMonstersArea().size()>=5){
			JOptionPane.showMessageDialog(null, "no empty monster space available");
			//throw new NoMonsterSpaceException();
			return false;
		}
		if(summ==true){
			JOptionPane.showMessageDialog(null, "Can't set multiple monsters");
			//throw new MultipleMonsterAdditionException();
			return false;
		}
		summ=true;
		
			Player active = Card.getBoard().getActivePlayer();
			MyButton mnstr = Card.getBoard().getMonster();
			//mnstr.setText("YUGIOH");
			mnstr.setIcon(new ImageIcon(monster.getName()+".png"));
			mnstr.setToolTipText(null);
			active.getField().getHand().remove(monster);
			active.getField().getpField().getHPanel().remove(mnstr);
			active.getField().getpField().getHPanel().getHandButtons().remove(mnstr);
			active.getField().getpField().getHPanel().revalidate();
			active.getField().getpField().getHPanel().repaint();
			Card.getBoard().getActivePlayer().getField().addMonsterToField(monster,Mode.DEFENSE,true);
			mnstr.setText("YUGIOH");
			mnstr.setToolTipText(null);
			active.getField().getpField().getMnstrPanel().addButton(mnstr);
			this.getField().getpField().getHPanel().formulate();
		return true;
	}
	public boolean setMonster(MonsterCard monster,ArrayList<MonsterCard> sacrifices)
	{
		//Defence means hidden ? wala malhash 3laka
		if(Card.getBoard().getActivePlayer().getField().getPhase()==Phase.BATTLE){
			JOptionPane.showMessageDialog(null, "Can't set monster in BATTLE phase");
			return false;
		}
		if(Card.getBoard().getActivePlayer()!=this)
			return false;
		if(Card.getBoard().getActivePlayer().getField().getMonstersArea().size()>=5){
			JOptionPane.showMessageDialog(null, "no empty monster space available");
			//throw new NoMonsterSpaceException();
			return false;
		}
		if(summ==true){
			JOptionPane.showMessageDialog(null, "Can't set multiple monsters");
			//throw new MultipleMonsterAdditionException();
			return false;
		}
		summ=true;
		Player active = Card.getBoard().getActivePlayer();
		MyButton mnstr = Card.getBoard().getMonster();
		//mnstr.setToolTipText(mnstr.getToolTipText()+"<br>"+"ATTACK");
		active.getField().getHand().remove(monster);
		active.getField().getpField().getHPanel().remove(mnstr);
		active.getField().getpField().getHPanel().getHandButtons().remove(mnstr);
		active.getField().getpField().getHPanel().revalidate();
		active.getField().getpField().getHPanel().repaint();
		Card.getBoard().getActivePlayer().getField().addMonsterToField(monster,Mode.DEFENSE,false);
		//mnstr.setText("YUGIOH");
		mnstr.setIcon(new ImageIcon(monster.getName()+".png"));
		mnstr.setToolTipText(null);
		active.getField().getpField().getMnstrPanel().addButton(mnstr);
		this.getField().getpField().getHPanel().formulate();
		//Card.getBoard().getActivePlayer().getField().addMonsterToField(monster,Mode.DEFENSE,sacrifices);
		return true;
	}
	public boolean endTurn()
	{
		
		if(Card.getBoard().getWinner()!=null)
			return false;
		if (Card.getBoard().getActivePlayer()!=this)
			return false;
		summ=false;
		int m=Card.getBoard().getActivePlayer().getField().getMonstersArea().size();
		for (int i=0;i<m;i++)
			Card.getBoard().getActivePlayer().getField().getMonstersArea().get(i).setFlag(false);
		Card.getBoard().getActivePlayer().getField().setPhase(Phase.MAIN1);
		Player temp=Card.getBoard().getActivePlayer();
		Card.getBoard().setActivePlayer(Card.getBoard().getOpponentPlayer());
		Card.getBoard().setOpponentPlayer(temp);
		Card.getBoard().getActivePlayer().getField().addNCardsToHand(1);
		Card.getBoard().hideHand();
		Card.getBoard().setSpell(null);
		Card.getBoard().setMonster(null);
		Card.getBoard().setDeclareNeedMnstr(false);
		Card.getBoard().setSplNeedMnstr(false);
		Card.getBoard().setSmnMnstrNeedSac(false);
		Card.getBoard().setSacrifices(new ArrayList<MonsterCard>());
		return true;
	}
	public void addCardToHand()
	{
		this.getField().addCardToHand();
	}
	public void addNCardsToHand(int n){
		this.getField().addNCardsToHand(n);
	}
	public void endPhase()
	{
		if (Card.getBoard().getWinner()!=null)
			return ;
		Phase cf=this.getField().getPhase();
		summ=false;
		if(cf==Phase.MAIN1)
			this.getField().setPhase(Phase.BATTLE);
		else if(cf==Phase.BATTLE)
			this.getField().setPhase(Phase.MAIN2);
		else{
			this.getField().setPhase(Phase.MAIN1);
		    Card.getBoard().nextPlayer();             
		}
		Card.getBoard().setDeclareNeedMnstr(false);
		Card.getBoard().setMonster(null);
		Card.getBoard().setSpell(null);
		Card.getBoard().setSplNeedMnstr(false);
		Card.getBoard().setSmnMnstrNeedSac(false);
		Card.getBoard().setSacrifices(new ArrayList<MonsterCard>());
	}
	
}
