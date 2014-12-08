package Beetle.Haggis.Client;

import java.awt.event.ActionEvent;

/**
 * @author Faruk
 * @version 1.0
 * @created 25-Okt-2014 19:32:33
 */
public class JoinGameModel {

	private String ipAdress;
	private String playerName;
	public JoinGame m_View;
	private GameFieldModel gfModel;

	/**
	 * 
	 * @param gfm
	 * @param veiwVisible
	 */
	public JoinGameModel(GameFieldModel gfm) {
		gfModel = gfm;
		m_View = new JoinGame(this);
	}

	public void setViewVisible(boolean visibel) {
		if (visibel) {
			m_View.setVisible(true);
		}
	}

	public void checkIP() {
		// Nice to have
	}

	public void joinGame() {
		m_View.dispose();
		

		// TODO 1 LL spiel beitreten.
		// fals es nicht geht --> fehlermeldung und fenster stehen lassen!
	}

	public void goBack() {
		new StartWindow(gfModel).setVisible(true);
		m_View.dispose();
	}

}// end JoinGameModel