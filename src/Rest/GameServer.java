package Rest;
import Message.GameState;
import Server.Card;
import Server.EventHandlerServer;
import Server.Player;
import Server.ClientConnection;

/**
 * @author Loïc
 * @version 1.0
 * @created 25-Okt-2014 19:32:33
 */
public class GameServer {

	private Card cardStack;
	private EventHandlerServer eventHandler;
	private Player player;
	private GameState state;
	public ClientConnection m_ClientConnection;
	public EventHandlerServer m_EventHandlerServer;
	public Listener m_Listener;
	public Player m_Player;
	public Card m_Card;

	public GameServer(){

	}

	public void finalize() throws Throwable {

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

	private void mixCards(){

	}

	private void newCards(){

	}
}//end GameServer