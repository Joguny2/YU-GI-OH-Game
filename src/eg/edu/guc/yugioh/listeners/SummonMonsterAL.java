package eg.edu.guc.yugioh.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.gui.MyButton;

public class SummonMonsterAL implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		Player active = Card.getBoard().getActivePlayer();
		MyButton mnstr = Card.getBoard().getMonster();
		int index=active.getField().getpField().getHPanel().getIndexOf(mnstr);
		if (mnstr==null)
		{
			JOptionPane.showMessageDialog(null,"You didn't select a monster card");
			return;
		}
		MonsterCard mnstrcard =(MonsterCard) active.getField().getHand().get(index);
		int level = mnstrcard.getLevel();
		if (level <= 4)
		{
			active.summonMonster(mnstrcard);
			Card.getBoard().setMonster(null);
			return;
		}
		if (level <=6)
		{
			if (active.getField().getMonstersArea().size()<1)
			{
				JOptionPane.showMessageDialog(null, "You don't have enough monsters to sacrifice");
				Card.getBoard().setMonster(null);
				return;
			}
			JOptionPane.showMessageDialog(null, "Choose one monster to sacrifice");
			Card.getBoard().setSmnMnstrNeedSac(true);
			return;
		}
		if (level <= 8) 
		{
			if (active.getField().getMonstersArea().size()<2)
			{
				JOptionPane.showMessageDialog(null, "You don't have enough monsters to sacrifice");
				Card.getBoard().setMonster(null);
				return;
			}
			JOptionPane.showMessageDialog(null, "Choose two monsters to sacrifice");
			Card.getBoard().setSmnMnstrNeedSac(true);
			return;
		}
	}

}
