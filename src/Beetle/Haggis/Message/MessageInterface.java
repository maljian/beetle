package Beetle.Haggis.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author Nadine Töpfer
 *
 */
public interface MessageInterface extends Remote {
	public Message receiveMessage() throws RemoteException;
	public void sendMessage(Message m) throws RemoteException;

}
