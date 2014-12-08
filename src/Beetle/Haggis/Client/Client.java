package Beetle.Haggis.Client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Beetle.Haggis.Message.GameState;
import Beetle.Haggis.Message.Message;
import Beetle.Haggis.Message.MessageInterface;
import Beetle.Haggis.Server.GameServer;

/**
 * 
 * @author Nadine Töpfer
 *
 */
public class Client {

		private int id; //PlayerID starting 0 
		private String playerName;
		private String serverIP;
		public static JoinGame m_JoinGame;
		/**
		 * 
		 * Start the Server in a new Thread Only one of the player gets the server
		 */
		private GameServer server;
		private GameState state;
		private Registry registry;
		private MessageInterface mi;
		private boolean stillRunning = true;
		private GameFieldModel gfModel;
		
		public void run() {
			String host = "127.0.0.1";
			stillRunning= true;
			String serverIP = m_JoinGame.txtIpAdress.getText();
			try {
//				String ipv4 = InetAddress.getLocalHost().toString();
//				String[] ip = ipv4.split("/");
//				host = ip[1];
				host = serverIP;
				registry = LocateRegistry.getRegistry(host);
				mi = (MessageInterface) registry.lookup("MessageInterface");
//				// TODO
//				mi.init();
			} catch (RemoteException | NotBoundException e) {
				System.err.println("Client exception: " + e.toString());
				e.printStackTrace();
				stillRunning= false; 
				//TODO 2 JoinGame Fenster neu aufrufen (+Fehlermeldung) IPAdresse, Spielername und ServerID uebergeben
				new JoinGameModel(gfModel);
			}
			
			
			while (stillRunning) {
				Message m;
				try {
					m = mi.receiveMessage();
					//TODO GUI aktualisieren
					//btn Pass Aktivieren
				} catch (RemoteException e) {
					e.printStackTrace();
				}
	 
			}
			
			
			
			

		}

		public Client(GameFieldModel gfModel) {
			super();
			this.gfModel = gfModel;
		}

		
}
