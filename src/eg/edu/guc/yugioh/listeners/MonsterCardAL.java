package eg.edu.guc.yugioh.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.gui.MyButton;

public class MonsterCardAL implements ActionListener
{
	public void actionPerformed(ActionEvent e) 
	{
		Player active = Card.getBoard().getActivePlayer();
		MyButton m = (MyButton) e.getSource();
		if (Card.getBoard().isSmnMnstrNeedSac())
		{
			
			MyButton mnstrb = Card.getBoard().getMonster();
			int index = active.getField().getpField().getHPanel().getIndexOf(mnstrb);
			int isac = active.getField().getpField().getMnstrPanel().getMonstersButtons().indexOf(m);
			if (isac == -1)
			{
				JOptionPane.showMessageDialog(null, "You can't sacrifice this monster");
				return;
			}
			MonsterCard mnstr=(MonsterCard) active.getField().getHand().get(index);
			int level=mnstr.getLevel();
			int sac;
			if (level<=6)
				sac = 1;
			else
				sac = 2;
			if (Card.getBoard().getSacrifices().contains(active.getField().getMonstersArea().get(isac)))
			{
				JOptionPane.showMessageDialog(null,"You've already chosen this monster");
				return;
			}
			
			Card.getBoard().getSacrifices().add(active.getField().getMonstersArea().get(isac));
			if (Card.getBoard().getSacrifices().size()==sac)
			{
				Card.getBoard().getActivePlayer().getField().removeMonsterToGraveyard(Card.getBoard().getSacrifices());
				active.summonMonster(mnstr,Card.getBoard().getSacrifices());
				active.getField().getpField().getHPanel().remove(mnstrb);
				active.getField().getpField().getHPanel().getHandButtons().remove(mnstrb);
				active.getField().getpField().getHPanel().formulate();
				active.getField().getpField().getHPanel().revalidate();
				active.getField().getpField().getHPanel().repaint();
				Card.getBoard().setSmnMnstrNeedSac(false);
				Card.getBoard().setSacrifices(new ArrayList<MonsterCard>());
			}
			
			return;
		}
		if (Card.getBoard().isMnstrNeedSac())
		{
			MyButton mnstrb = Card.getBoard().getMonster();
			int index = active.getField().getpField().getHPanel().getIndexOf(mnstrb);
			int isac = active.getField().getpField().getMnstrPanel().getMonstersButtons().indexOf(m);
			if (isac == -1)
			{
				JOptionPane.showMessageDialog(null, "You can't sacrifice this monster");
				return;
			}
			MonsterCard mnstr=(MonsterCard) active.getField().getHand().get(index);
			int level=mnstr.getLevel();
			int sac;
			if (level<=6)
				sac = 1;
			else
				sac = 2;
			if (Card.getBoard().getSacrifices().contains(active.getField().getMonstersArea().get(isac)))
			{
				JOptionPane.showMessageDialog(null,"You've already chosen this monster");
				return;
			}
			Card.getBoard().getSacrifices().add(active.getField().getMonstersArea().get(isac));
			if (Card.getBoard().getSacrifices().size()==sac)
			{
				Card.getBoard().getActivePlayer().getField().removeMonsterToGraveyard(Card.getBoard().getSacrifices());
				active.setMonster(mnstr,Card.getBoard().getSacrifices());
				active.getField().getpField().getHPanel().remove(mnstrb);
				active.getField().getpField().getHPanel().getHandButtons().remove(mnstrb);
				active.getField().getpField().getHPanel().formulate();
				active.getField().getpField().getHPanel().revalidate();
				active.getField().getpField().getHPanel().repaint();
				Card.getBoard().setMnstrNeedSac(false);
				Card.getBoard().setSacrifices(new ArrayList<MonsterCard>());
			}
			return;
		}
		if (Card.getBoard().isSplNeedMnstr())
		{
			MyButton splbutton = Card.getBoard().getSpell();
			int indexspl = Card.getBoard().getActivePlayer().getField()
					.getpField().getSplPanel().getSpellsButtons().indexOf(splbutton);
			int indexhand = Card.getBoard().getActivePlayer().getField()
					.getpField().getHPanel().getHandButtons().indexOf(splbutton);
			SpellCard splcard;
			if (indexspl==-1)//located in hand
			{
				splcard = (SpellCard) Card.getBoard().getActivePlayer().getField().getHand().get(indexhand);
				Card.getBoard().getActivePlayer().setSpell(splcard);
			}
			else
				splcard = Card.getBoard().getActivePlayer().getField().getSpellArea().get(indexspl);
			if (splcard.getName().equals("Change Of Heart"))
			{
				int mnstrindex = Card.getBoard().getOpponentPlayer().
						getField().getpField().getMnstrPanel().getMonstersButtons().indexOf(m);
				if (mnstrindex==-1)
				{
					JOptionPane.showMessageDialog(null, "You should choose one of your opponent's monsters in field");
					
					return;
				}
				else
				{
					MonsterCard mnstrcard = Card.getBoard().getOpponentPlayer().getField().getMonstersArea().get(mnstrindex);
					Card.getBoard().getActivePlayer().activateSpell(splcard,mnstrcard);
					Card.getBoard().setSpell(null);
					Card.getBoard().setSplNeedMnstr(false);
					return;
				}
			}
			else if (splcard.getName().equals("Mage Power"))
			{
				int mnstrindex = Card.getBoard().getActivePlayer().
						getField().getpField().getMnstrPanel().getMonstersButtons().indexOf(m);
				if (mnstrindex==-1)
				{
					JOptionPane.showMessageDialog(null, "You should choose one of your monsters in field");
				
					return;
				}
				MonsterCard mnstrcard = Card.getBoard().getActivePlayer().getField().getMonstersArea().get(mnstrindex);
				Card.getBoard().getActivePlayer().activateSpell(splcard,mnstrcard);
				Card.getBoard().setSpell(null);
				Card.getBoard().setSplNeedMnstr(false);
				return;
			}
		}
		else if (Card.getBoard().isDeclareNeedMnstr())
		{
			MyButton oppbutton = m;
			MyButton accbutton = Card.getBoard().getMonster();
			int indexopp = Card.getBoard().getOpponentPlayer().getField()
					.getpField().getMnstrPanel().getMonstersButtons().indexOf(m);
			int indexac = Card.getBoard().getActivePlayer().getField()
					.getpField().getMnstrPanel().getMonstersButtons().indexOf(accbutton);
			if (indexopp==-1)
			{
				JOptionPane.showMessageDialog(null, "You can't attack this monster");
				return;
			}
			MonsterCard activeMonster = Card.getBoard().getActivePlayer().getField()
					.getMonstersArea().get(indexac);
			MonsterCard opponentMonster = Card.getBoard().getOpponentPlayer().getField()
					.getMonstersArea().get(indexopp);
			Card.getBoard().getActivePlayer().declareAttack(activeMonster, opponentMonster);
			Card.getBoard().setDeclareNeedMnstr(false);
			Card.getBoard().setMonster(null);
			return;
		}
		Card.getBoard().setMonster(m);
		int indexhand=active.getField().getpField().getHPanel().getIndexOf(m);
		int indexmnstr=active.getField().getpField().getMnstrPanel().getMonstersButtons().indexOf(m);
		if (indexhand == -1)//not in hand
		{
			if (indexmnstr != -1)
			{
				Card.getBoard().setMonster(m);
				return;
			}
			JOptionPane.showMessageDialog(null,Card.getBoard().getBtnPanel().getIP().getTurn().getText());
			Card.getBoard().setMonster(null);
			return;
		}
		//Card.getBoard().setMonster(null);
	}

}
