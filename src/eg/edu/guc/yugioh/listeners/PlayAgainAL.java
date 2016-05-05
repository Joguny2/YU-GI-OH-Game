package eg.edu.guc.yugioh.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.gui.StartGame;

public class PlayAgainAL implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Card.getBoard().getMyEndGame().dispose();
			new StartGame();
		} catch (IOException | UnexpectedFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	

}
