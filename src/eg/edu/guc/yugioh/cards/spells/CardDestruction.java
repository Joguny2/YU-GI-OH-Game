package eg.edu.guc.yugioh.cards.spells;

import java.util.ArrayList;

import eg.edu.guc.yugioh.board.player.Field;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.gui.MyButton;

public class CardDestruction extends SpellCard{

	public CardDestruction(String name, String description) {
		super(name, description);                             
		
	}
	public void action(MonsterCard monster)
	{
		Field field1=getBoard().getActivePlayer().getField();
		ArrayList <Card> hand1 =field1.getHand();
		Field field2=getBoard().getOpponentPlayer().getField();
		ArrayList <Card> hand2 =field2.getHand();
		int numCardsActive=hand1.size();
		int numCardsOpponent=hand2.size();
		field1.removeHandToGraveyard(hand1);                          
		field2.removeHandToGraveyard(hand2);
		
		for(int i=0;i<numCardsActive;i++){
			field1.addCardToHand();
		}
		for(int i=0;i<numCardsOpponent;i++){
			field2.addCardToHand();
		}
		
		}
	
	

}
