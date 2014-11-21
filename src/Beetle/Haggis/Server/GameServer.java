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
	private Player[] players;
	private GameState state;
	public ClientConnection m_ClientConnection;
	public EventHandlerServer m_EventHandlerServer;
	public Listener m_Listener;
	private int targetPoint = 0;

	// public Player m_Player;
	// public Card m_Card;

	public GameServer() {

	}

	/**
	 * Punkte Karten zuweisen
	 */
	private void assignCards() {

	}

	private int calculate(ArrayList<Card> cards) {
		int totalPoint=0;
		for (Card card : cards) {
			totalPoint += card.getValue();
		}
		return totalPoint;
	}

	private Stack<Card> distributeCards(Stack <Card> cardStack) {
		
		Collections.shuffle(cardStack);
		for (Player p : players) {
			Stack<Card> playerCard = new Stack<Card>();
			for (int i = 0; i < 14; i++) {
				playerCard.push(cardStack.pop());
			}
			for (int i=0; i<3;i++){
				playerCard.push(new Card());
			}
			// TODO add Joker to each player
			p.setCards(playerCard);
		}
		return cardStack;

	}

	/**
	 * Construktor
	 * 
	 * @param targetPoint
	 * @param bet
	 * @param bombs
	 * @param playerNr
	 */
	public void GamServer(int targetPoint, boolean bet, boolean bombs,
			int playerNr) {
		this.targetPoint = targetPoint;
		players = new Player[playerNr];
		state = new GameState(players);
		startNewRound(0);
		
	}
	
	private void startNewRound(int payerTur){
		Stack<Card> cardStack=	newCards();
		cardStack= distributeCards(cardStack);
		int restVal = 0;
		for (Card card : cardStack) {
			restVal= card.getValue();
		}
		state.newRound(0, restVal);
	}

	/**
	 * Reihenfolge Spieler
	 */
	public void logic() {

	}


	private Stack<Card> newCards() {
		 Stack<Card> cardStack= null;

		return cardStack;
	}
}// end GameServer