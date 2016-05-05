package eg.edu.guc.yugioh.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.listeners.EXITAL;
import eg.edu.guc.yugioh.listeners.PlayAgainAL;

public class EndGame extends JFrame {
	public EndGame()
	{
		super();
		setResizable(true);
		setSize(1200,1200);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		JPanel p=new JPanel();
		
		p.add(new JLabel("The winner is "+Card.getBoard().getWinner().getName()));  //throws a nullpointerexception since there is no winner yet
		add(p);
		
		JPanel p2=new JPanel();
		p2.setLayout(new FlowLayout());
		p2.setVisible(true);
		JButton b=new JButton();
		b.setVisible(true);
		b.add(new JLabel("Exit"));
		b.addActionListener(new EXITAL(this));
		p2.add(b);
		JButton b2=new JButton();
		b2.setVisible(true);
		b2.add(new JLabel("Play Again"));
		b2.addActionListener(new PlayAgainAL());
		p2.add(b2);
		add(p2);
		
		
	}
	public static void main(String[] args)throws IOException , UnexpectedFormatException{
		new EndGame();
	}

}
