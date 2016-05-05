package eg.edu.guc.yugioh.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.board.player.Phase;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.gui.MyButton;

public class DeclareAttackAL implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		if (Card.getBoard().getMonster()==null)
		{
			JOptionPane.showMessageDialog(null, "You didn't choose a monster card");
			return;
		}
		Player active = Card.getBoard().getActivePlayer();
		MyButton m = Card.getBoard().getMonster(); 
		int index = active.getField().getpField().getMnstrPanel().getMonstersButtons().indexOf(m);
		if (index == -1)
		{
			JOptionPane.showMessageDialog(null, "You can't attack with a monster in hand");
			return;
		}
		MonsterCard monster = active.getField().getMonstersArea().get(index);
		if (Card.getBoard().getOpponentPlayer().getField().getMonstersArea().isEmpty())
		{
			Card.getBoard().getActivePlayer().declareAttack(monster);
			Card.getBoard().setMonster(null);
		}
		else
		{
			if (active.getField().getPhase()!=Phase.BATTLE)
			{
				JOptionPane.showMessageDialog(null, "You can'tattack in this phase");
				return;
			}
			JOptionPane.showMessageDialog(null, "Select the monster you want to attack");
			Card.getBoard().setDeclareNeedMnstr(true);
		}
		
	}

}
