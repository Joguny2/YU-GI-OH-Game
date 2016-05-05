package eg.edu.guc.yugioh.cards.spells;

import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.gui.MyButton;

public class ChangeOfHeart extends SpellCard {

	public ChangeOfHeart(String name, String description) {
		super(name, description);
		
	}
	public void action(MonsterCard monster)
	{
		int index = getBoard().getOpponentPlayer().getField().getMonstersArea().indexOf(monster);
		MyButton mnstrbutton = getBoard().getOpponentPlayer().getField().getpField()
				.getMnstrPanel().getMonstersButtons().get(index);
		Player opp = getBoard().getOpponentPlayer();
		Player act = getBoard().getActivePlayer();
		opp.getField().getpField().getMnstrPanel().removeButton(monster, opp.getField());
		opp.getField().getMonstersArea().remove(monster);
		act.getField().getpField().getMnstrPanel().addButton(mnstrbutton);
		getBoard().getActivePlayer().getField().getMonstersArea().add(monster);
		
		
	}

}
