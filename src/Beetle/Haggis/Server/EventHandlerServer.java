package Beetle.Haggis.Server;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

import javax.swing.JOptionPane;

import Beetle.Haggis.Message.Message;
import Beetle.Haggis.Message.MessageInterface;

/**
 * @author Nadine Töpfer
 * @version 1.0

 */
public class EventHandlerServer implements MessageInterface{

	private GameServer main;
	private Registry registry;
	private SynchronousQueue<Message> sendQueue;
	private SynchronousQueue<Message> receiveQueue;
	
	public EventHandlerServer(){
		super();
	}

	public void startServer(int targetPoint, boolean bet, boolean bombs, int numberPlayer){
		
		try{
			main = new GameServer(targetPoint, bet, bombs, numberPlayer);
			MessageInterface stub = (MessageInterface) UnicastRemoteObject.exportObject(main, 0);
			
			//Bind the remote object's stub in the registry
			registry = LocateRegistry.getRegistry();
			registry.bind("MessageInterface", stub);
			
			System.err.println("Server ready");
		}catch (Exception e){
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
		
	}
	
	//muss noch aufgerufen werden
	public void stopServer(){
		try {
			registry.unbind("MessageInterface");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Information", "Die Verbindung zum Server wurde unterbrochen.", JOptionPane.INFORMATION_MESSAGE);	
		}

	}
	
//	// An alle Clients Message schicken mit aktuellen Stand
//	public void updateCilent(){
//		
//
//	}

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
		return main.initPlayer(name);
		
		
		// Spieler muss sich beim GameServer irgendwie anmelden
		// Hier muss sich der Client dann bei irgendwem anmelden.
		// wichtig ist, dass das Objekt, wo sich der Client anmelden muss,
		// dieser Klasse hier bekannt ist und über alle Instanzen geshart wird.
		// D.h. es muss static sein.
		// Für jede neue Netzwerkverbindung wird ein neues Objekt des Typs
		// GameServer angelegt, dieses hat alle statischen Felder der Klasse
		// auch.
		// Die nichtstatischen Felder, wie z.B. die queues, sind privat für die
		// entsprechende Netzwerkverbindung.

	}

	
}//end EventHandlerServer