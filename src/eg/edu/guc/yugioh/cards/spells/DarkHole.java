package eg.edu.guc.yugioh.cards.spells;

import java.util.ArrayList;

import eg.edu.guc.yugioh.board.player.Field;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class DarkHole extends Raigeki {

	public DarkHole(String name, String description) {
		super(name, description);
		
	}
	public void action(MonsterCard monster)
	{
		super.action(null);
		Field f=getBoard().getActivePlayer().getField();
		ArrayList<MonsterCard> monsters=f.getMonstersArea(); 
		f.removeMonsterToGraveyard(monsters);
		
		
	}

}

