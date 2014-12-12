package Beetle.Haggis.Client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

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
//		private String playerName;
//		private String serverIP;
		public static JoinGame m_JoinGame;
		/**
		 * 
		 * Start the Server in a new Thread Only one of the player gets the server
		 */
		private GameServer server;
		private GameState state;
		private Registry registry;
		private MessageInterface mi;
		private boolean connected = true;
		private GameFieldModel gfModel;
		public GameField m_GameField;
		
		public void connect(String name) {
			
			String host = "127.0.0.1";
			connected = true;
			String serverIP = m_JoinGame.txtIpAdress.getText();
			try {
				host = serverIP;
				registry = LocateRegistry.getRegistry(host);
				mi = (MessageInterface) registry.lookup("MessageInterface");
				id = mi.init(m_JoinGame.txtPlayerName.getText());
				
			} catch (RemoteException | NotBoundException e) {
				System.err.println("Client exception: " + e.toString());
				e.printStackTrace();
				connected= false; 
				JOptionPane.showMessageDialog(null, "Fehlermeldung", "Es konnte keine Verbindung hergestellt werden, bitte gib deine Daten neu ein.", JOptionPane.ERROR_MESSAGE);
				new JoinGameModel(gfModel);
			}
			
			while (connected) {
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
		
		// Thread erstellen, entlosschlaufe durchlaufen pull state
		// schauen ob neue Version, falls ja gui aktualisieren

		public Client(GameFieldModel gfModel) {
			super();
			this.gfModel = gfModel;
		}

		
}
