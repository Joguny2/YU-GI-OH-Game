package eg.edu.guc.yugioh.cards.spells;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class GracefulDice extends SpellCard{
	public GracefulDice(String name, String description) {
		super(name, description);                         //make sure this is correct
		
	}

	public void action(MonsterCard monster)
	{
		int n=(int)(Math.random()*10)+1;
		//ArrayList<MonsterCard>
		ArrayList<MonsterCard> monsters=getBoard().getActivePlayer().getField().getMonstersArea();//check references
		for(int i=0;i<monsters.size();i++){
			int d=monsters.get(i).getDefensePoints();
			d+=n*100;
			int a=monsters.get(i).getAttackPoints();
			a+=n*100;
			monsters.get(i).setDefensePoints(d);
			monsters.get(i).setAttackPoints(a);
			Player active = Card.getBoard().getActivePlayer();
			active.getField().getpField().getMnstrPanel().getMonstersButtons().get(i).setToolTipText(monsters.get(i).getInfo());
		}
		getBoard().getActivePlayer().getField().getpField().getMnstrPanel().revalidate();
		getBoard().getActivePlayer().getField().getpField().getMnstrPanel().repaint();
		
	}

}
