package eg.edu.guc.yugioh.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.gui.*;
public class SpellCardAL implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		Player active = Card.getBoard().getActivePlayer();
		MyButton m = (MyButton) e.getSource();
		if (Card.getBoard().isSplNeedMnstr())
		{
			JOptionPane.showMessageDialog(null,"You chose a spell card instead of monster");
			Card.getBoard().setSpell(null);
			Card.getBoard().setSplNeedMnstr(false);
		}
		Card.getBoard().setSpell((MyButton) e.getSource());
		int indexhand=active.getField().getpField().getHPanel().getIndexOf(m);
		int indexspell=active.getField().getpField().getSplPanel().getSpellsButtons().indexOf(m);
		if (indexhand == -1 && indexspell==-1)
		{
			JOptionPane.showMessageDialog(null,Card.getBoard().getBtnPanel().getIP().getTurn().getText());
			Card.getBoard().setSpell(null);
			return;
		}
		
	}

}
