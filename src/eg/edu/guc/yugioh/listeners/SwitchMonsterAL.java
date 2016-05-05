package eg.edu.guc.yugioh.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.cards.Card;

public class SwitchMonsterAL implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(Card.getBoard().getMonster()==null){
			JOptionPane.showMessageDialog(null, "You didn't choose a monster!");
			return;
		}
		
		int index=Card.getBoard().getActivePlayer().getField().getpField()
				.getMnstrPanel().getMonstersButtons().indexOf(Card.getBoard().getMonster());
		Card.getBoard().getActivePlayer().switchMonsterMode
		(Card.getBoard().getActivePlayer().getField().getMonstersArea().get(index));
		Card.getBoard().setMonster(null);
		

	}

}
