package Beetle.Haggis.Server;
import java.util.ArrayList;

import Beetle.Haggis.Message.GameState;

/**
 * @author  Loïc Lavanchy
 * @version 1.0
 * @created 25-Okt-2014 19:32:33
 */
public class GameServer {

	private ArrayList<Card> cardStack;
	private EventHandlerServer eventHandler;
	private Player[] player;
	private GameState state;
	public ClientConnection m_ClientConnection;
	public EventHandlerServer m_EventHandlerServer;
	public Listener m_Listener;
//	public Player m_Player;
//	public Card m_Card;

	public GameServer(){

	}


	/**
	 * Punkte Karten zuweisen
	 */
	private void assignCards(){

	}

	private void calculate(){

	}

	/**
	 * Card[]
	 */
	public boolean checkCombination(){
		return false;
	}

	private void distributeCards(){

	}

	/**
	 * Construktor
	 * 
	 * @param targetPoint
	 * @param bet
	 * @param bombs
	 * @param playerNr
	 */
	public void GamServer(int targetPoint, boolean bet, boolean bombs, int playerNr){

	}

	/**
	 * Reihenfolge Spieler
	 */
	public void logic(){

	}


	public boolean createTable(int amountPlayer, int targetPoint){
		return true;
	}
	
	private void newCards(){

	}
}//end GameServer