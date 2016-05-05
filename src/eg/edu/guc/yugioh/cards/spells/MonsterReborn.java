package eg.edu.guc.yugioh.cards.spells;

import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.gui.MyButton;

public class MonsterReborn extends SpellCard{
	
public MonsterReborn(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}

public void action(MonsterCard monster){
	if(Card.getBoard().getActivePlayer().getField().getGraveyard().isEmpty() && Card.getBoard().getOpponentPlayer().getField().getGraveyard().isEmpty()){
		JOptionPane.showMessageDialog(null, "GRAVEYARDS are empty");
		return;
	}
	MonsterCard m=new MonsterCard("","",0,0,0); 
	int attack=0;
	boolean location=false;
	for(Card c : getBoard().getActivePlayer().getField().getGraveyard())
	{
        if(c instanceof MonsterCard){
        	if(((MonsterCard) c).getAttackPoints()>attack){
        		m=(MonsterCard)c;
        		
        		attack=m.getAttackPoints();
        	}
        }
    }
	for(Card c : getBoard().getOpponentPlayer().getField().getGraveyard()){
        if(c instanceof MonsterCard){
        	if(((MonsterCard) c).getAttackPoints()>attack){
        		m=(MonsterCard)c;
        		attack=m.getAttackPoints();
        		location=true;
        	}
        }
    }
	if(location)
		getBoard().getOpponentPlayer().getField().getGraveyard().remove(m);
	else
		getBoard().getActivePlayer().getField().getGraveyard().remove(m);
	getBoard().getActivePlayer().getField().getMonstersArea().add(m);
	MyButton mnstr=new MyButton(m.getName());
	mnstr.setToolTipText(m.getInfo());
	Card.getBoard().getActivePlayer().getField().getpField().getMnstrPanel().getMonstersButtons().add(mnstr);
	Card.getBoard().getActivePlayer().getField().getpField().getMnstrPanel().addButton(mnstr);
	Card.getBoard().updateGraveyard();
	m.setLocation(Location.FIELD);
	
}

}
