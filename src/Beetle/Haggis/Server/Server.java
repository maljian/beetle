package Beetle.Haggis.Server;

import java.rmi.RemoteException;

import Beetle.Haggis.Message.Message;
import Beetle.Haggis.Message.MessageInterface;

/**
 * 
 * @author Nadine Töpfer
 *
 */
public class Server implements MessageInterface{

	public Message receiveMessage() throws RemoteException{
		Message m = new Message();
		return m;
	}
	
	public void sendMessage(Message m) throws RemoteException{
		
	}
	
	public Server(){
		
	}
}
