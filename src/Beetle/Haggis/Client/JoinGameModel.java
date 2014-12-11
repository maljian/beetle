package Beetle.Haggis.Client;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

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

	//Nice to have...
//	public boolean checkIP() {
//		
//	}

	public void joinGame() {
		m_View.dispose();
		

		// TODO 1 LL spiel beitreten.
		// TODO FFD (erledigt) falls es nicht geht --> fehlermeldung und fenster stehen lassen!
//		if (joinGame() == null){
//			new JoinGameModel(gfModel).setViewVisible(true);
//			JOptionPane.showMessageDialog(null, "IP-Adresse stimmt nicht oder Spiel wurde noch nicht erstellt. Bitte versuchen Sie es nochmal.");
//			
//		}
	}

	public void goBack() {
		new StartWindow(gfModel).setVisible(true);
		m_View.dispose();
	}

}// end JoinGameModel