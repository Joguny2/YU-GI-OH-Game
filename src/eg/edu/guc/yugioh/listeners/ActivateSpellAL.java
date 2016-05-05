package eg.edu.guc.yugioh.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.gui.MyButton;

public class ActivateSpellAL implements ActionListener{

	public void actionPerformed(ActionEvent e) 
	{
		Player active = Card.getBoard().getActivePlayer();
		MyButton spl = Card.getBoard().getSpell();
		int indexhand=active.getField().getpField().getHPanel().getIndexOf(spl);
		int indexspl=active.getField().getpField().getSplPanel().getSpellsButtons().indexOf(spl);
		if (spl==null)
		{
			JOptionPane.showMessageDialog(null,"You didn't select a spell card");
			return;
		}
		//JOptionPane.showMessageDialog(null,index);
		if (indexhand==-1)
		{
			SpellCard splcard =(SpellCard) active.getField().getSpellArea().get(indexspl);
			if (!splcard.getName().equals("Mage Power") && !splcard.getName().equals("Change Of Heart"))
			{
				//active.setSpell(splcard);
				active.activateSpell(splcard,null);
				Card.getBoard().setSpell(null);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Please choose the monster card");
				Card.getBoard().setSplNeedMnstr(true);
			}
		}
		else if (indexspl==-1)
		{
			SpellCard splcard =(SpellCard) active.getField().getHand().get(indexhand);
			if (!splcard.getName().equals("Mage Power") && !splcard.getName().equals("Change Of Heart"))
			{
				//JOptionPane.showMessageDialog(null,"Change Of heart in hand");
				active.setSpell(splcard);
				active.activateSpell(splcard,null);
				Card.getBoard().setSpell(null);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Please choose the monster card");
				Card.getBoard().setSplNeedMnstr(true);
			}
		}
			
		
		
		Card.getBoard().hideHand();
		
	}

}
