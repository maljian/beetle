package Beetle.Haggis.Network;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.SynchronousQueue;

import javax.swing.JOptionPane;

import Beetle.Haggis.Message.Message;
import Beetle.Haggis.Message.MessageInterface;

/**
 * @author Nadine Töpfer
 * 
 */
public class EventHandlerServer implements MessageInterface {

	private GameServer gameServer;
	private Registry registry;
	private SynchronousQueue<Message> sendQueue;
	private SynchronousQueue<Message> receiveQueue;

	public EventHandlerServer() {
		super();
	}

	/**
	 * 
	 * @param targetPoint
	 * @param bet
	 * @param bombs
	 * @param numberPlayer
	 * @throws Exception
	 */
	public void startServer(int targetPoint, boolean bet, boolean bombs,
			int numberPlayer) throws Exception {

		gameServer = new GameServer(targetPoint, bet, bombs, numberPlayer);
		MessageInterface stub = (MessageInterface) UnicastRemoteObject
				.exportObject(gameServer, 0);

		// Bind the remote object's stub in the registry
		String registeryName= "MessageInterface";
		registry = LocateRegistry.createRegistry(1099); 
		LocateRegistry.getRegistry();
		registry.bind(registeryName, stub);
		
		System.out.println("Startserver ready");

	}

	public void stopServer() {
		try {
			registry.unbind("MessageInterface");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Die Verbindung zum Server wurde unterbrochen.", "Information",
					
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	/**
	 * This is a remote method which is executed by the client. It will block
	 * until there is content in the sendQueue and then pass the message to the
	 * client.
	 */
	public Message receiveMessage() throws RemoteException {
		Message m = sendQueue.poll();
		return m;
	}

	/**
	 * This is a remote method which is executed by the client. It will receive
	 * the message and pass it to the receiveQueue where it will then be
	 * processed by the game logic.
	 */
	public void sendMessage(Message m) throws RemoteException {
		try {
			receiveQueue.put(m);
		} catch (InterruptedException e) {
			sendMessage(m);
		}
	}

	public int init(String name) throws RemoteException {
		return gameServer.initPlayer(name);

	}

}
