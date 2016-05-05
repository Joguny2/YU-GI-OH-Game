package eg.edu.guc.yugioh.cards.spells;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.board.player.Field;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class Raigeki extends SpellCard{
	public Raigeki(String name, String description) {
		super(name, description);
		
	}

	public void action(MonsterCard monster)
	{
		Player temp=getBoard().getActivePlayer();
		Field f=getBoard().getOpponentPlayer().getField();
		ArrayList<MonsterCard> monsters=f.getMonstersArea();
		f.removeMonsterToGraveyard(monsters);
		
	}

}
