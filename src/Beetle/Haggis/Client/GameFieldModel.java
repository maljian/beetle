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
	

	public GameFieldModel(){
		

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
	
	
	

}//end GameFieldModel