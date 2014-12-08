package Beetle.Haggis.Server;
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
public class EventHandlerServer {

	private GameServer main;
	private Registry registry;
	public EventHandlerServer(){
		
	}

	public void startServer(int targetPoint, boolean bet, boolean bombs, int numberPlayer){
		try{
			main = new GameServer(targetPoint, bet, bombs, numberPlayer);
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
			
		}

	}
	// An alle Clients Message schicken mit aktuellen Stand
	public void updateCilent(){

	}
}//end EventHandlerServer