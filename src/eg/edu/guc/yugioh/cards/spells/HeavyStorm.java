package eg.edu.guc.yugioh.cards.spells;


import eg.edu.guc.yugioh.board.player.Field;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class HeavyStorm extends HarpieFeatherDuster{

	public HeavyStorm(String name, String description) {
		super(name, description);
		
	}
	public void action(MonsterCard monster)
	{
		super.action(null);
		Field f2=getBoard().getActivePlayer().getField();
		f2.removeSpellToGraveyard(f2.getSpellArea());
		Field f1=getBoard().getOpponentPlayer().getField();
		f1.removeSpellToGraveyard(f1.getSpellArea());
		
	}

}
