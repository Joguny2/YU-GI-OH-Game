package eg.edu.guc.yugioh.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.board.player.Deck;
import eg.edu.guc.yugioh.board.player.Field;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;
import eg.edu.guc.yugioh.cards.spells.CardDestruction;
import eg.edu.guc.yugioh.cards.spells.ChangeOfHeart;
import eg.edu.guc.yugioh.cards.spells.DarkHole;
import eg.edu.guc.yugioh.cards.spells.HeavyStorm;
import eg.edu.guc.yugioh.cards.spells.MagePower;
import eg.edu.guc.yugioh.cards.spells.PotOfGreed;
import eg.edu.guc.yugioh.cards.spells.Raigeki;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.gui.ButtonsPanel;
import eg.edu.guc.yugioh.gui.DuringGame;
import eg.edu.guc.yugioh.gui.EndGame;
import eg.edu.guc.yugioh.gui.HandPanel;
import eg.edu.guc.yugioh.gui.MyButton;
import eg.edu.guc.yugioh.gui.PlayerField;

public class Board 
{
	public ArrayList<MonsterCard> getSacrifices() {
		return sacrifices;
	}
	public void setSacrifices(ArrayList<MonsterCard> sacrifices) {
		this.sacrifices = sacrifices;
	}
	private DuringGame myDuringGame;
	public DuringGame getMyDuringGame() {
		return myDuringGame;
	}
	public void setMyDuringGame(DuringGame myDuringGame) {
		this.myDuringGame = myDuringGame;
	}
	private Player activePlayer;
	private Player opponentPlayer;
	private Player winner;
	private MyButton Spell;
	private MyButton Monster;
	private EndGame myEndGame;
	public EndGame getMyEndGame() {
		return myEndGame;
	}
	public void setMyEndGame(EndGame myEndGame) {
		this.myEndGame = myEndGame;
	}
	private boolean SplNeedMnstr;
	private boolean declareNeedMnstr;
	private boolean SmnMnstrNeedSac;
	private boolean MnstrNeedSac;
	private ArrayList<MonsterCard> sacrifices = new ArrayList<MonsterCard>();
	public boolean isSmnMnstrNeedSac() {
		return SmnMnstrNeedSac;
	}
	public void setSmnMnstrNeedSac(boolean smnMnstrNeedSac) {
		SmnMnstrNeedSac = smnMnstrNeedSac;
	}
	public boolean isDeclareNeedMnstr() {
		return declareNeedMnstr;
	}
	public void setDeclareNeedMnstr(boolean declareNeedMnstr) {
		this.declareNeedMnstr = declareNeedMnstr;
	}
	public boolean isSplNeedMnstr() {
		return SplNeedMnstr;
	}
	public void setSplNeedMnstr(boolean splNeedMnstr) {
		SplNeedMnstr = splNeedMnstr;
	}
	public MyButton getMonster() {
		return Monster;
	}
	public void setMonster(MyButton monster) {
		Monster = monster;
	}
	private ButtonsPanel BtnPanel;
	private boolean flag=false;
	public void hideHand()
	{
		ArrayList<MyButton> activeButtons = activePlayer.getField().getpField().getHPanel().getHandButtons();
		ArrayList<MyButton> opponentButtons = opponentPlayer.getField().getpField().getHPanel().getHandButtons();
		for (int i=0;i<opponentButtons.size();i++)
		{
			opponentButtons.get(i).setToolTipText(null);
			//opponentButtons.get(i).setText("YugiOh");
			opponentButtons.get(i).setIcon(new ImageIcon("img/Card Back.png"));
		}
		for (int i=0;i<activeButtons.size();i++)
		{
			Card c = activePlayer.getField().getHand().get(i);
			//activeButtons.get(i).setText(c.getName());
			activeButtons.get(i).setIcon(new ImageIcon("img/"+c.getName()+".png"));
			if (c instanceof SpellCard)
				activeButtons.get(i).setToolTipText(c.getName());
			else
			{
				MonsterCard c1 = (MonsterCard) c; 
				activeButtons.get(i).setToolTipText(c1.getInfo());
			}
		}
		activePlayer.getField().getpField().getHPanel().formulate();
		opponentPlayer.getField().getpField().getHPanel().formulate();
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public ButtonsPanel getBtnPanel() {
		return BtnPanel;
	}
	public void setBtnPanel(ButtonsPanel btnPanel) {
		BtnPanel = btnPanel;
	}
	
	public Board()
	{
		Card.setBoard(this);
	}
	public void whoStarts(Player p1,Player p2)
	{
		Random r=new Random();
		Player s = r.nextBoolean() ? p1 : p2;
		activePlayer=s;
		opponentPlayer= (s==p1) ? p2 : p1;
	}
	
	public void nextPlayer()
	{
		Player temp = activePlayer;
		activePlayer=opponentPlayer;
		opponentPlayer=temp;
		activePlayer.getField().addCardToHand();
		hideHand();
	}
	
	public Player getActivePlayer() {
		return activePlayer;
	}
	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}
	public Player getOpponentPlayer() {
		return opponentPlayer;
	}
	public void setOpponentPlayer(Player opponentPlayer) {
		this.opponentPlayer = opponentPlayer;
	}
	public Player getWinner() {
		return winner;
	}
	public void setWinner(Player winner)
	{
		this.winner = winner;	
		myDuringGame.dispose();
		myEndGame = new EndGame();
	}
	public void startGame(Player p1, Player p2)throws IOException
	{
		
		this.whoStarts(p1,p2);
		Field f1=activePlayer.getField();
		Field f2=opponentPlayer.getField();
		
		f1.setpField(new PlayerField(1,activePlayer.getName()));
		f2.setpField(new PlayerField(2,opponentPlayer.getName()));
		f1.addNCardsToHand(5);
		f2.addNCardsToHand(5);
	
		f1=activePlayer.getField();
		f1.addCardToHand();
		
	}
	public MyButton getSpell() {
		return Spell;
	}
	public void setSpell(MyButton spell) {
		Spell = spell;
	}
	public void updateGraveyard(){
		Player active=Card.getBoard().getActivePlayer();
		Player opponent=Card.getBoard().getOpponentPlayer();
		int size=active.getField().getGraveyard().size();
		JButton b=active.getField().getpField().getGraveButton();
		if(size==0){
			b.setText("GRAVEYARD");
		}
		else{
		Card c=active.getField().getGraveyard().get(size-1);
		//b.setText(c.getName());
		b.setIcon(new ImageIcon("img/"+c.getName()+".png"));
		b.setToolTipText(c.getInfo());
		}
		size=opponent.getField().getGraveyard().size();
		JButton b1=opponent.getField().getpField().getGraveButton();
		if(size==0){
			b1.setText("GRAVEYARD");
		}
		else{
		Card c1=opponent.getField().getGraveyard().get(size-1);
		//b1.setText(c1.getName());
		b1.setIcon(new ImageIcon("img/"+c1.getName()+".png"));
		b1.setToolTipText(c1.getInfo());
		}
	}
	public boolean isMnstrNeedSac() {
		return MnstrNeedSac;
	}
	public void setMnstrNeedSac(boolean mnstrNeedSac) {
		MnstrNeedSac = mnstrNeedSac;
	}

}
