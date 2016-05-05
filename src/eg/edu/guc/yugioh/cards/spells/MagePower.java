package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.gui.MyButton;

public class MagePower extends SpellCard{

	public MagePower(String name, String description) {
		super(name, description);
		
	}
	public void action(MonsterCard monster){
		//MonsterCard m=getBoard().getActivePlayer().getField().getMonstersArea().get(0); // does this change in the actual monstercard??
		MonsterCard m =monster;
		int n=getBoard().getActivePlayer().getField().getSpellArea().size();
		int a=m.getAttackPoints();
		a+=(500*n);
		m.setAttackPoints(a);
		int d=m.getDefensePoints();
		d+=(500*n);
		m.setDefensePoints(d);
		int index = getBoard().getActivePlayer().getField().getMonstersArea().indexOf(monster);
		MyButton b  = Card.getBoard().getActivePlayer().getField().getpField().getMnstrPanel().getMonstersButtons().get(index);
		b.setToolTipText(monster.getInfo());
	
	}
	

}
