package Beetle.Haggis.Client;

/**
 * @author Faruk
 * @version 1.0
 * @created 25-Okt-2014 19:32:33
 */
public class JoinGameModel {

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

	public void joinGame() {

		String name = m_View.txtPlayerName.getText();
		String serverIP = m_View.txtIpAdress.getText();
		boolean joined = gfModel.joinGame(name, serverIP);
		if (joined) {
			m_View.dispose();
		}

	}

	public void goBack() {
		new StartWindow(gfModel).setVisible(true);
		m_View.dispose();
	}

}