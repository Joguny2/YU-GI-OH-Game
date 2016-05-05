package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;

abstract public class SpellCard extends Card{
	public SpellCard(String name, String description){
		super(name,description);
	}
	public String getInfo()
	{
		return this.getName();
		
	}
	abstract public void action(MonsterCard monster);

}
