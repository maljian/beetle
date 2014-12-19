package Beetle.Haggis.Network;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

import Beetle.Haggis.Client.GameFieldModel;
import Beetle.Haggis.Client.JoinGame;
import Beetle.Haggis.Client.JoinGameModel;
import Beetle.Haggis.Client.NewGame;
import Beetle.Haggis.Message.GameState;
import Beetle.Haggis.Message.Message;
import Beetle.Haggis.Message.Message.PlayedAction;
import Beetle.Haggis.Message.MessageInterface;

/**
 * 
 * @author Nadine Töpfer
 *
 */
public class Client extends Thread {

	private int id;
	public static JoinGame m_JoinGame;
	public static NewGame m_NewGame;
	private GameState state;
	private Registry registry;
	private MessageInterface mi;
	private boolean connected = true;
	private GameFieldModel gfModel;

	/**
	 * 
	 * @param name
	 * @param serverIP
	 * @return
	 */
	public boolean connect(String name, String serverIP) {

		boolean connected = false;
		if (serverIP == null || serverIP.length() == 0) {
			JOptionPane.showMessageDialog(null,
					"Bitte geben Sie eine gültige IP-Adresse an.",
					"Information", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} 
		try {
			registry = LocateRegistry.getRegistry(serverIP);
			mi = (MessageInterface) registry.lookup("MessageInterface");
			id = mi.init(name);
			gfModel.setId(id);

		} catch (RemoteException | NotBoundException e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
			connected = true;
			JOptionPane
					.showMessageDialog(
							null,
							"Es konnte keine Verbindung hergestellt werden, versuchen Sie es nochmals.",
							"Fehlermeldung, Server Verbindung",
							JOptionPane.ERROR_MESSAGE);
			new JoinGameModel(gfModel);
		}
		return connected;

	}

	public void run() {
		while (connected) {
			Message m;
			try {
				m = mi.receiveMessage();
				Thread.sleep(1000);
				if (state == null
						|| m.getGameState().getVersion() > state.getVersion()) {
					state = m.getGameState();

					if (m.getPlayedAction() == PlayedAction.WIN) {
						gfModel.anounceWinner(state);
					} else {
						gfModel.actualizeView(state);
					}
				}
			} catch (RemoteException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param m
	 */
	public void sendMessage(Message m) {
		try {
			mi.sendMessage(m);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		;
	}

	public Client(GameFieldModel gfModel) {
		super();
		this.gfModel = gfModel;
	}

}
