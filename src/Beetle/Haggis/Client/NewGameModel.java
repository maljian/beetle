package Beetle.Haggis.Client;

import java.awt.EventQueue;
import java.net.InetAddress;
import java.net.UnknownHostException;

import Beetle.Haggis.Server.EventHandlerServer;
import Beetle.Haggis.Server.GameServer;

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

		try {
			String ipv4 = InetAddress.getLocalHost().toString();
			String[] ip = ipv4.split("/");
			m_View.txtIpAdress.setText(ip[1]);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author Nadine Töpfer
	 */
	protected void startServer() {
		int numberPlayer = Integer.parseInt(m_View.NumberPlayer.getSelectedItem().toString());
		System.out.println(numberPlayer);		
		int targetPoint = Integer.parseInt( m_View.TargetPoint.getSelectedItem().toString());
		boolean bet = false;	//Preparation for future version 
		boolean bombs = false;  //Preparation for future version  
		EventHandlerServer ehs = new EventHandlerServer();
		ehs.startServer(targetPoint, bet, bombs, numberPlayer);
		m_View.dispose();
	}
	
	public void goBack() {
		new StartWindow(gfModel).setVisible(true);
		m_View.dispose();
	}
	// gehört zum Server
	// public void newGame(){
	// GameServer server = new GameServer(targetPoint, false, false,
	// numberPlayer);
	//
	//
	// }
}// end NewGameModel
