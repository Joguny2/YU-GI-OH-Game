package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.board.player.Field;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class HarpieFeatherDuster extends SpellCard{

	public HarpieFeatherDuster(String name, String description) {
		super(name, description);
		
	}
	public void action(MonsterCard monster){
		Field f=getBoard().getOpponentPlayer().getField();
		f.removeSpellToGraveyard(f.getSpellArea());
		
		
		
	}

}
