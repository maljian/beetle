package Beetle.Haggis.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MessageInterfaceImpl extends UnicastRemoteObject implements MessageInterface{

	protected MessageInterfaceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Message receiveMessage() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendMessage(Message m) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
