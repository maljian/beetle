package Beetle.Haggis.Server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

import Beetle.Haggis.Client.GameField;
import Beetle.Haggis.Client.GameFieldModel;
import Beetle.Haggis.Client.JoinGame;
import Beetle.Haggis.Client.JoinGameModel;
import Beetle.Haggis.Message.GameState;
import Beetle.Haggis.Message.Message;
import Beetle.Haggis.Message.MessageInterface;

/**
 * 
 * @author Nadine Töpfer
 *
 */
public class Client extends Thread {

	private int id; // PlayerID starting 0
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

	public void connect(String name, String serverIP) {

		String host = "127.0.0.1";
		connected = true;
		// serverIP = m_JoinGame.txtIpAdress.getText();
		try {
			host = serverIP;
			registry = LocateRegistry.getRegistry(host);
			mi = (MessageInterface) registry.lookup("MessageInterface");
			id = mi.init(name);

		} catch (RemoteException | NotBoundException e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
			connected = false;
			JOptionPane
					.showMessageDialog(
							null,
							"Es konnte keine Verbindung hergestellt werden, versuchen Sie es nochmals.",
							"Fehlermeldung, Server Verbindung",
							JOptionPane.ERROR_MESSAGE);
			new JoinGameModel(gfModel);
		}

	}

	public void run() {
		while (connected) {
			Message m;
			try {
				m = mi.receiveMessage();
				System.out.println(m.getGameState().getVersion());
				Thread.sleep(1000);
				if (m.getGameState().getVersion() > state.getVersion()) {
					
				}
				// btn Pass Aktivieren
			} catch (RemoteException | InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	public Client(GameFieldModel gfModel) {
		super();
		this.gfModel = gfModel;
	}

}
