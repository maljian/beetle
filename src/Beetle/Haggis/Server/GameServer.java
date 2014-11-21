package Beetle.Haggis.Server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

import com.sun.org.apache.xml.internal.utils.StylesheetPIHandler;

import Beetle.Haggis.Message.GameState;

/**
 * @author Loïc Lavanchy
 * @version 1.0
 * @created 14-Nov-2014
 */
public class GameServer {

	private EventHandlerServer eventHandler;
	//private Player[] players;
	private GameState state;
	public ClientConnection m_ClientConnection;
	public EventHandlerServer m_EventHandlerServer;
	public Listener m_Listener;
	//private int targetPoint = 0;

	// public Player m_Player;
	// public Card m_Card;

	public GameServer() {

	}

	/**
	 * Get the value of the cards and addition them.
	 * @param cardStack Cards to evaluate 
	 * @return Total value of the cards
	 */
	private int CalculateStackValue(Stack<Card> cardStack) {
		int totalPoint = 0;
		for (Card card : cardStack) {
			totalPoint += card.getValue();
		}
		return totalPoint;
	}

	/**
	 * Construktor
	 * 
	 * @param targetPoint
	 * @param bet Inactive, only in the next version.
	 * @param bombs Inactive, only in the next version.
	 * @param playerNr Amount of players in the game, 2 or 3. Default are 2 
	 */
	public void GamServer(int targetPoint, boolean bet, boolean bombs,
			int playerNr) {
		targetPoint = targetPoint;
		playerNr =playerNr==2 || playerNr ==3 ? playerNr: 2 ;
		state = new GameState(new Player[playerNr]);
		startNewRound(0);

	}

	/**
	 * Create a new Card stack and distribute the cards to the player.
	 * @param payerTurn With player start the next round
	 */
	private void startNewRound(int payerTurn) {
		Stack<Card> cardStack = newCards(state.getPlayers().length);
		state = distributeCards(cardStack, state);
		state.newRound(payerTurn);
	}

	/**
	 * Give new cards to the Players. the old cards are not saved.
	 * @param cardStack A Stack with all cards.
	 * @param state The Actual gam state 
	 * @return The player have new Cards in the Gamestate
	 */
	private GameState distributeCards(Stack<Card> cardStack, GameState state) {
		Collections.shuffle(cardStack);
		Player[] players= state.getPlayers();
		for (Player p : players ) {
			Stack<Card> playerCard = new Stack<Card>();
			for (int i = 0; i < 14; i++) {
				playerCard.push(cardStack.pop());
			}
			for (int i = 0; i < 3; i++) {
				playerCard.push(new Card());// TODO add Joker to each player
			}
			
			p.setCards(playerCard);
		}
		state.setPlayerS(players);
		state.setRestCardValue(CalculateStackValue(cardStack));
		return state;
	}

	/**
	 * Reihenfolge Spieler
	 */
	public void logic() {

	}

	/**
	 * 
	 * @param playerAmount Amount of paling Person
	 * @return
	 */
	private Stack<Card> newCards( int playerAmount ) {
		Stack<Card> cardStack = null;

		return cardStack;
	}
}// end GameServer