package Beetle.Haggis.Server;

import java.rmi.RemoteException;

import Beetle.Haggis.Message.Message;
import Beetle.Haggis.Message.MessageInterface;
import java.util.concurrent.SynchronousQueue;
/**
 * 
 * @author Nadine Töpfer
 *
 */
public class Server implements MessageInterface{
	private String username = "";
	
	private SynchronousQueue<Message> queue;
	public Message receiveMessage() throws RemoteException{
		Message m = queue.poll();
		return m;
	}
	
	public void sendMessage(Message m) throws RemoteException{
		
	}
	
	public void setUserName (String s) throws RemoteException{
		username = s;
	}
	
	public Server(){
		
	}
}
// queue erzeugen, wo Meldungen generiert werden
// im GameServer anmelden und da ne queue anlegen