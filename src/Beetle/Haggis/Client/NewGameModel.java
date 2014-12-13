package Beetle.Haggis.Client;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import Beetle.Haggis.Server.Client;
import Beetle.Haggis.Server.EventHandlerServer;

/**
 * 
 * @author Faruk Doganci
 *
 */
public class NewGameModel extends JoinGameModel {
	private int numberPlayer;
	private int targetPoint;

	public static NewGame m_View;
	public GameFieldModel gfModel;
	private Client client;

	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// m_NewGame = new NewGame();
	// m_NewGame.setVisible(true);
	// try {
	// String ipv4 = InetAddress.getLocalHost().toString();
	// String [] ip = ipv4.split("/");
	// m_NewGame.txtIpAdress.setText(ip[1]);
	// } catch (UnknownHostException e) {
	// e.printStackTrace();
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	public NewGameModel(GameFieldModel gfModel) {
		super(gfModel);
		this.gfModel = gfModel;
		m_View = new NewGame(this);
		m_View.setVisible(true);
		client = new Client(gfModel);

		try {
			String ipv4 = InetAddress.getLocalHost().toString();
			String[] ip = ipv4.split("/");
			m_View.txtIpAdress.setText(ip[1]);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author Nadine T�pfer
	 */
	protected void startServer() {
		int numberPlayer = Integer.parseInt(m_View.numberPlayer
				.getSelectedItem().toString());
		int targetPoint = Integer.parseInt(m_View.TargetPoint.getSelectedItem()
				.toString());
		boolean bet = false; // Preparation for future version
		boolean bombs = false; // Preparation for future version
		EventHandlerServer ehs = new EventHandlerServer();
		try {
			ehs.startServer(targetPoint, bet, bombs, numberPlayer);
			String serverIP = m_View.txtIpAdress.getText();
			client.connect(m_View.txtPlayerName.getText(), serverIP); 
			m_View.dispose();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,  "Der Server konnte nicht gestartet werden. Bitter versuchen Sie es erneut.", "Information",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void goBack() {
		new StartWindow(gfModel).setVisible(true);
		m_View.dispose();
	}
	// geh�rt zum Server
	// public void newGame(){
	// GameServer server = new GameServer(targetPoint, false, false,
	// numberPlayer);
	//
	//
	// }
}// end NewGameModel
