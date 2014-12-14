package Beetle.Haggis.Client;

import Beetle.Haggis.Server.Client;

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
	private Client client;

	/**
	 * 
	 * @param gfm
	 * @param veiwVisible
	 */
	public JoinGameModel(GameFieldModel gfm) {
		gfModel = gfm;
		m_View = new JoinGame(this);
		client = new Client(gfModel);
	}

	public void setViewVisible(boolean visibel) {
		if (visibel) {
			m_View.setVisible(true);
		}
	}

	public void joinGame() {

		String name = m_View.txtPlayerName.getText();
		String serverIP = m_View.txtIpAdress.getText();
		client.connect(name, serverIP);
		m_View.dispose();

		// TODO LL sicherstellen das man zurück zum Gamefleld (model) kommt
		// new JoinGameModel(gfModel).setViewVisible(true);

	}

	public void goBack() {
		new StartWindow(gfModel).setVisible(true);
		m_View.dispose();
	}

}// end JoinGameModel