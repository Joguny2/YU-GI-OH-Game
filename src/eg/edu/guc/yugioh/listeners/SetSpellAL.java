package eg.edu.guc.yugioh.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.gui.CardType;
import eg.edu.guc.yugioh.gui.MyButton;

public class SetSpellAL implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Player active = Card.getBoard().getActivePlayer();
		MyButton spl = Card.getBoard().getSpell();
		int index=active.getField().getpField().getHPanel().getIndexOf(spl);
		if (index==-1)
		{
			JOptionPane.showMessageDialog(null,"The spell card u chose cannot be set");
			return;
		}
		if (spl==null)
		{
			JOptionPane.showMessageDialog(null,"You didn't select a spell card");
			return;
		}
		SpellCard splcard =(SpellCard) active.getField().getHand().get(index);
		active.setSpell(splcard);
		Card.getBoard().setSpell(null);
		return;
	}

}
