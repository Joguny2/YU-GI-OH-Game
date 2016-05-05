package eg.edu.guc.yugioh.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
public class HandPanel extends JLayeredPane
{
	private ArrayList<MyButton> handButtons;
	
	
	public void formulate()
	{
		
		int space = 0;
		int s = handButtons.size();
		int added=490/s;
		for (int i=0;i<s;i++)
		{
			MyButton m = handButtons.get(i);
			m.setBounds(space, 0, 100, 120);
			//add(m);
			space+=added;
		}
	}
	
	public HandPanel()throws IOException
	{
		handButtons  = new ArrayList<MyButton>();
		//setBorder(BorderFactory.createLineBorder(Color.black));
		this.setVisible(true);
		this.setSize(120,120);

	}
	public ArrayList<MyButton> getHandButtons() {
		return handButtons;
	}
	public void setHandButtons(ArrayList<MyButton> handButtons) {
		this.handButtons = handButtons;
	}
	public static void main(String[] args)throws IOException
	{
		new HandPanel();
	}


}
