package eg.edu.guc.yugioh.gui;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.board.player.Field;
import eg.edu.guc.yugioh.cards.MonsterCard;


public class MonstersPanel extends JPanel
{
	private ArrayList<MyButton> monstersButtons;
	public ArrayList<MyButton> getMonstersButtons() {
		return monstersButtons;
	}
	public void setMonstersButtons(ArrayList<MyButton> monstersButtons) {
		this.monstersButtons = monstersButtons;
	}
	public MonstersPanel()
	{
		monstersButtons = new ArrayList<MyButton>();
		this.setLayout(new GridLayout(1,6));
	
		this.setVisible(true);
	    add(new JLabel("MonstersArea"));
	    
	    for (int i=0;i<5;i++)
	    {
	    	MyButton m = new MyButton("Monster");
	    	m.setEnabled(false);
	    	m.setCtype(CardType.MONSTER);
	    	monstersButtons.add(m);
	    	add(m);
	    }
	}
	public void removeButton(MonsterCard c,Field f)
	{
		int index = f.getMonstersArea().indexOf(c);
		MyButton m = monstersButtons.get(index);
		remove(m);
		monstersButtons.remove(m);
		MyButton n = new MyButton("Monster");
    	n.setEnabled(false);
    	n.setCtype(CardType.MONSTER);
    	monstersButtons.add(n);
    	add(n);
    	revalidate();
    	repaint();
	}
	public void addButton(MyButton m)
	{
		int x = 0;
		while (monstersButtons.get(x).isEnabled())
		{
			x++;
		}
		for (int i=0;i<5;i++)
		{
			remove(monstersButtons.get(i));
		}
		monstersButtons.set(x, m);
		for (int i=0;i<5;i++)
			add(monstersButtons.get(i));
		repaint();
		revalidate();
		
	}
}
