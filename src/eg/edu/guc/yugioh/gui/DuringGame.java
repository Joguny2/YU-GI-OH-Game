
	package eg.edu.guc.yugioh.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;

public class DuringGame extends JFrame{
	String first;
	String second;
	public DuringGame(String p1,String p2) throws IOException , UnexpectedFormatException
	{
		super();
		setResizable(true);
		setSize(1200,1200);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BoxLayout(getContentPane(),BoxLayout.X_AXIS));
		JPanel Board = new JPanel();
		Board.setLayout(new GridLayout(2,1));
		new Board();
		Card.getBoard().startGame(new Player(p1),new Player(p2));
		Card.getBoard().setBtnPanel(new ButtonsPanel(p1,p2));
		Card.getBoard().setMyDuringGame(this);
		this.add(Card.getBoard().getBtnPanel());
		Player active = Card.getBoard().getActivePlayer();
		Player opponent = Card.getBoard().getOpponentPlayer();
		
		Board.add(active.getField().getpField());
	
		Board.add(opponent.getField().getpField());
		active.getField().getpField().getDeckButton().setText("Deck "+active.getField().getDeck().getDeck().size());
		Card.getBoard().hideHand();

		this.add(Board);
		
		
	}
	
	

}





