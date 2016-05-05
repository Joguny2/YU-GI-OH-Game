package eg.edu.guc.yugioh.cards.spells;



import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class PotOfGreed extends SpellCard {
	public PotOfGreed(String name, String description) { //check if this is correct
		super(name, description);
		
	}

	public void action(MonsterCard monster)
	{
		for(int i=0;i<2;i++){
		getBoard().getActivePlayer().getField().addCardToHand();  //check
		}
		
		
		}
	}


