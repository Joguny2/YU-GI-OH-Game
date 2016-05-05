package eg.edu.guc.yugioh.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;

public class EndPhaseAL implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Card.getBoard().getActivePlayer().endPhase();
		Player Active = Card.getBoard().getActivePlayer();
		Card.getBoard().getBtnPanel().getIP().getPhase().setText(Active.getField().getPhase().toString());
		Card.getBoard().getBtnPanel().getIP().getTurn().setText("It's "+Active.getName()+"'s turn");
	}

}
