package Beetle.Haggis.Client;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Beetle.Haggis.Message.GameState;
import Beetle.Haggis.Message.Message;
import Beetle.Haggis.Message.MessageInterface;
import Beetle.Haggis.Server.Card;
import Beetle.Haggis.Server.GameServer;

/**
 * @author 
 * @version 1.0
 * @created 25-Okt-2014 19:32:32
 */
public class GameFieldModel {

	private Card cardsCenter;
	/**
	 * Array
	 */
	private Card handCards;
	/**
	 * Array
	 */
	private Card selectedCards;
	
	/**
	 * Gets from server
	 */
	
	// Teil Nadine
	private int id; //PlayerID starting 0 
	private String playerName;
	private String serverIP;
	/**
	 * 
	 * Start the Server in a new Thread Only one of the player gets the server
	 */
	private GameServer server;
	private GameState state;
	private Registry registry;
	private MessageInterface mi;
	private boolean stillRunning = true;
	// Ende Teil Nadine

	public GameFieldModel(){
		run();

	}

	/**
	 * GameServer
	 */
	public void checkCard(){	
		//TODO (NT) Karten hinzufügen → Funktion checkKombination (im GameState) → true - Knopf legen aktivieren → false deaktivieren

	}

	public void layCards(){
		//TODO Karten übergeben (im GameState speichern) und an Message weitergeleitet zum senden
		// mi.sendMessage(m); m = Message
	}

	public void pass(){
		//TODO Message absenden → keine Parameter(View) noetig, aber Message sagen ueberspringen
		// mi.sendMessage(m); m = Message
	}
	
	//TODO neue Private) funktion knoepfe deaktivieren (passen legen, alenfals cards) 
	
	/**
	 * @author Nadine Töpfer
	 */
	public void run() {
		String host = "127.0.0.1";
		stillRunning= true;
		try {
//			String ipv4 = InetAddress.getLocalHost().toString();
//			String[] ip = ipv4.split("/");
//			host = ip[1];
			host = serverIP;
			registry = LocateRegistry.getRegistry(host);
			mi = (MessageInterface) registry.lookup("MessageInterface");
			// TODO
			mi.setUserName(null);
		} catch (RemoteException | NotBoundException e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
			stillRunning= false; 
			//TODO JoinGame Fenster neu aufrufen (+Fehlermeldung) IPAdresse, Spielername und ServerID uebergeben
		}
		
		
		while (stillRunning) {
			Message m;
			try {
				m = mi.receiveMessage();
				//TODO GUI aktualisieren
				//btn Pass Aktivieren
			} catch (RemoteException e) {
				e.printStackTrace();
			}
 
		}

	}

}//end GameFieldModel