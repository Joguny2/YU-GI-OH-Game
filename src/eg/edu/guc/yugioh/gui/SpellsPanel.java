package eg.edu.guc.yugioh.gui;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.board.player.Field;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;

public class SpellsPanel extends JPanel
{
	private ArrayList<MyButton> spellsButtons;
	public SpellsPanel()
	{
		spellsButtons = new ArrayList<MyButton>();
		this.setLayout(new GridLayout(1,6));
		this.setVisible(true);
		add(new JLabel("SpellsArea"));
		for (int i=0;i<5;i++)
		{
		    MyButton m = new MyButton("Spell");
		    m.setCtype(CardType.SPELL);
		    spellsButtons.add(m);
		    m.setEnabled(false);
		    add(m);
		    this.validate(); //check this out
		    
		}
	}
	public void removeButton(SpellCard c,Field f)
	{
		int index = f.getSpellArea().indexOf(c);
		MyButton m = spellsButtons.get(index);
		remove(m);
		spellsButtons.remove(m);
		MyButton n = new MyButton("Spell");
    	n.setEnabled(false);
    	n.setCtype(CardType.SPELL);
    	spellsButtons.add(n);
    	add(n);
    	//JOptionPane.showInputDialog("HIIIIII");
    	revalidate();
    	repaint();
	}
	public void addButton(MyButton m)
	{
		
		int x = 0;
		while (spellsButtons.get(x).isEnabled())
		{
			x++;
		}
		for (int i=0;i<5;i++)
		{
			remove(spellsButtons.get(i));
		}
		m.setIcon(new ImageIcon("Card Back.png"));
		spellsButtons.set(x,m);
		for (int i=0;i<5;i++){
			
			add(spellsButtons.get(i));
		}
		repaint();
		revalidate();
		
	}
	public ArrayList<MyButton> getSpellsButtons() {
		return spellsButtons;
	}
	public void setSpellsButtons(ArrayList<MyButton> spellsButtons) {
		this.spellsButtons = spellsButtons;
	}
		
	}


