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
	private Registry registry;
	public EventHandlerServer(){
		
	}

	// Diese Methode muss von irgendwem aufgerufen werden, das ist bisher noch nicht der Fall!
	public void startServer(int targetpoint, boolean bet, boolean bombs, int playerNr){
		try{
			main = new GameServer(targetpoint, bet, bombs, playerNr);
			MessageInterface stub = (MessageInterface) UnicastRemoteObject
					.exportObject(main, 0);
			
			//Bind the remote object's stub in the registry
			registry = LocateRegistry.getRegistry();
			registry.bind("MessageInterface", stub);
			
			System.err.println("Server ready");
		}catch (Exception e){
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
		

	}
	// Dito
	public void stopServer(){
		try {
			registry.unbind("MessageInterface");
		} catch (Exception e) {
			// TODO Hier was sinnvolles machen
		}

	}
	// ?
	public void updateCilent(){

	}
}//end EventHandlerServer