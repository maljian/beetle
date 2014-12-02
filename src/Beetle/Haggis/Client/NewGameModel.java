package Beetle.Haggis.Client;

import java.awt.EventQueue;
import java.net.InetAddress;
import java.net.UnknownHostException;

import Beetle.Haggis.Server.GameServer;

public class NewGameModel {
	private int numberPlayer;
	private int targetPoint;
	public static NewGame m_NewGame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					m_NewGame = new NewGame();
					m_NewGame.setVisible(true);
					try {
						String ipv4 = InetAddress.getLocalHost().toString();
						String [] ip = ipv4.split("/");
						m_NewGame.txtIpAdress.setText(ip[1]);
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block Löschen verändern!!!
						e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public void newGame(){
		GameServer server = new GameServer(targetPoint, false, false, numberPlayer);
		
	}
}//end NewGameModel
