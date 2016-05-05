package eg.edu.guc.yugioh.gui;
import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.cards.Card;

public class PlayerField extends JPanel
{
	LifePointsAndPlayer LP ;
	HandPanel HPanel;
	JButton DeckButton;
	JButton GraveButton;
	JPanel deckAndGraveyard;
	SpellsPanel splPanel;
	MonstersPanel mnstrPanel;
	public HandPanel getHPanel() {
		return HPanel;
	}
	public void setHPanel(HandPanel hPanel) {
		HPanel = hPanel;
	}
	public SpellsPanel getSplPanel() {
		return splPanel;
	}
	public void setSplPanel(SpellsPanel splPanel) {
		this.splPanel = splPanel;
	}
	public MonstersPanel getMnstrPanel() {
		return mnstrPanel;
	}
	public void setMnstrPanel(MonstersPanel mnstrPanel) {
		this.mnstrPanel = mnstrPanel;
	}
	public PlayerField()throws IOException
	{
		//Card.getBoard().setFlag(true);
		//this.HPanel = new HandPanel();
		DeckButton = new JButton("Deck 15");
		DeckButton.setIcon(new ImageIcon("Card Back.png"));
		DeckButton.setOpaque(false);
		JPanel deckAndGraveyard=new JPanel();
		
	}
	public PlayerField(int plyr,String name)throws IOException
	{
		Card.getBoard().setFlag(true);
		DeckButton = new JButton("Deck 15");
		//DeckButton.setIcon(new ImageIcon("Card Back.png"));
		DeckButton.setOpaque(false);
		JPanel deckAndGraveyard=new JPanel();
		LP = new LifePointsAndPlayer(name);
		if (plyr==1)
		{
			//setBorder(BorderFactory.createLineBorder(Color.blue));
			setLayout(new FlowLayout());
			//deckAndGraveyard.setBorder(BorderFactory.createLineBorder(Color.black));
			deckAndGraveyard.setLayout(new GridLayout(3,1));
			deckAndGraveyard.add(LP);
			DeckButton.setPreferredSize(new Dimension(100,120));
			deckAndGraveyard.add(DeckButton);
			GraveButton = new JButton("Graveyard");
			GraveButton.setPreferredSize(new Dimension(100,120));
			deckAndGraveyard.add(GraveButton);
			deckAndGraveyard.setVisible(true);
			HPanel = new HandPanel();
			/*
			
			hand1.add(new HandPanel());*/
			JPanel hand = new JPanel();
			//hand.setBorder(BorderFactory.createLineBorder(Color.black));
			hand.setLayout(new GridLayout(3,1));
			hand.add(HPanel);
			splPanel=new SpellsPanel();
			hand.add(splPanel);
			mnstrPanel = new MonstersPanel();
			hand.add(mnstrPanel);
			hand.setVisible(true);
			add(hand);
			add(deckAndGraveyard);
		}
		else
		{
			//setBorder(BorderFactory.createLineBorder(Color.black));
			setLayout(new FlowLayout());
			JPanel hand=new JPanel();
			HPanel = new HandPanel();
			//hand.setBorder(BorderFactory.createLineBorder(Color.black));
			hand.setLayout(new GridLayout(3,1));
			splPanel=new SpellsPanel();
			
			mnstrPanel = new MonstersPanel();
			hand.add(mnstrPanel);
			hand.add(splPanel);
			hand.add(HPanel);
			hand.setVisible(true);
			add(hand);
			
			
			//deckAndGraveyard.setBorder(BorderFactory.createLineBorder(Color.black));
			deckAndGraveyard.setLayout(new GridLayout(3,1));
			DeckButton.setPreferredSize(new Dimension(100, 120));
			GraveButton = new JButton("Graveyard");
			GraveButton.setPreferredSize(new Dimension(100, 120));
			deckAndGraveyard.add(GraveButton);
			deckAndGraveyard.add(DeckButton);
			deckAndGraveyard.add(LP);
			deckAndGraveyard.setVisible(true);
			add(deckAndGraveyard);
		}
			
	}

	public JButton getGraveButton() {
		return GraveButton;
	}
	public void setGraveButton(JButton graveButton) {
		GraveButton = graveButton;
	}
	public JPanel getDeckAndGraveyard() {
		return deckAndGraveyard;
	}
	public void setDeckAndGraveyard(JPanel deckAndGraveyard) {
		this.deckAndGraveyard = deckAndGraveyard;
	}
	public JButton getDeckButton() {
		return DeckButton;
	}

	public void setDeckButton(JButton deckButton) {
		DeckButton = deckButton;
	}

	public LifePointsAndPlayer getLP() {
		return LP;
	}

	public void setLP(LifePointsAndPlayer lP) {
		LP = lP;
	}
}
