package eg.edu.guc.yugioh.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;


public class StartGame extends JFrame {
	private String p1;
	private String p2;
	
	
	public StartGame() throws IOException , UnexpectedFormatException
	{
		
		super("YUGIOH");
		Board b=new Board();
		setResizable(true);
		setSize(1000,500);
		
		this.setBackground(Color.BLACK);
		this.setForeground(Color.BLACK);
		revalidate();
		repaint();
		this.setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JFrame me = this;
		
		JPanel p11 = new JPanel();
		p11.setOpaque(false);
		p11.revalidate();
		p11.repaint();
		JTextField text1 = new JTextField(20);
		p11.add(new JLabel("Enter First Player's name"));
		p11.add(text1);
		add(p11);
		JPanel p22 = new JPanel();
		JTextField text2 = new JTextField(20);
		p22.add(new JLabel("Enter Second Player's name"));
		p22.add(text2);
		add(p22);
		JPanel p3 = new JPanel();
		JButton start=new JButton("start");

		p3.add(start);
		add(p3);
	
		this.setVisible(true);
		
		ActionListener action = 
	             new ActionListener()
	            {
	                public void actionPerformed(ActionEvent e)
	               {
	                	p1=text1.getText();
	                	p2=text2.getText();
	                  if(e.getSource()==start){
	                try {
						new DuringGame(p1,p2);
						me.dispose();
					} catch (IOException | UnexpectedFormatException e1) {
						e1.printStackTrace();
					}
	                
	                  }
	               }
	               };
     start.addActionListener(action);
     

	}
	
	public String getP1() {
		return p1;
	}

	public String getP2() {
		return p2;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}
	
	public static void main(String[] args)throws IOException , UnexpectedFormatException
	{
		new StartGame();
	}
	

}

