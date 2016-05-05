package eg.edu.guc.yugioh.gui;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import eg.edu.guc.yugioh.cards.Location;

public class MyButton extends JButton{
	private CardType Ctype;
	private Location loc;
	public Location getLoc() {
		return loc;
	}
	public void setLoc(Location loc) {
		this.loc = loc;
	}
	public CardType getCtype() {
		return Ctype;
	}
	public void setCtype(CardType ctype) {
		Ctype = ctype;
	}
	public MyButton(String text){
		super();
		//setText(text);
		setOpaque(false);
		setIcon(new ImageIcon(text+".png"));
		revalidate();
		repaint();
		setMargin(new Insets(0, 0, 0, 0));
		setVisible(true);
		setPreferredSize(new Dimension(100, 120));
	}

}
