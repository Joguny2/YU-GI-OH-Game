package eg.edu.guc.yugioh.gui;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.cards.Card;

public class InfoPanel extends JPanel
{
	private JLabel Turn;
	private JLabel Phase;
	public InfoPanel()
	{
		//setBorder(BorderFactory.createLineBorder(Color.black));
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		JPanel J1=new JPanel();
		Turn = new JLabel("It's "+Card.getBoard().getActivePlayer().getName()+"'s turn");
		J1.add(Turn);
		add(J1);
		JPanel J2=new JPanel();
		Phase = new JLabel(Card.getBoard().getActivePlayer().getField().getPhase().toString());
		J2.add(Phase);
		add(J2);
	}
	public JLabel getTurn() {
		return Turn;
	}
	public void setTurn(JLabel turn) {
		Turn = turn;
	}
	public JLabel getPhase() {
		return Phase;
	}
	public void setPhase(JLabel phase) {
		Phase = phase;
	}
}
