package Beetle.Haggis.Server;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

import Beetle.Haggis.Message.Message;
import Beetle.Haggis.Message.MessageInterface;

/**
 * @author 
 * @version 1.0
 * @created 25-Okt-2014 19:32:32
 */
public class EventHandlerServer {

	private GameServer main;

	public EventHandlerServer(){
		
	}

	private void startServer(SynchronousQueue<Message> queue){
		try{
			Server obj = new Server();
			MessageInterface stub = (MessageInterface) UnicastRemoteObject
					.exportObject(obj, 0);
			
			//Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("MessageInterface", stub);
			
			System.err.println("Server ready");
		}catch (Exception e){
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
		

	}

	private void stopServer(){

	}

	public void updateCilent(){

	}
}//end EventHandlerServer