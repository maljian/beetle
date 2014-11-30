package Beetle.Haggis.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.sun.org.apache.xml.internal.utils.StylesheetPIHandler;

import Beetle.Haggis.Message.GameState;
import Beetle.Haggis.Message.Message.PlayedAction;
import Beetle.Haggis.Server.Card.Colour;

/**
 * @author Loïc Lavanchy
 * @version 1.0
 * @created 14-Nov-2014
 */
public class GameServer {

	private EventHandlerServer eventHandler;
	
	private GameState state;
	private int targetPoint;
//	public ClientConnection m_ClientConnection;
//	public EventHandlerServer m_EventHandlerServer;
//	public Listener m_Listener;

	// private Player[] players;
	// private int targetPoint = 0;
	// public Player m_Player;
	// public Card m_Card;

	public GameServer() {

	}

	/**
	 * Get the value of the cards and addition them.
	 * 
	 * @param cardStack
	 *            Cards to evaluate
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
	 * @param bet
	 *            Inactive, only in the next version.
	 * @param bombs
	 *            Inactive, only in the next version.
	 * @param playerNr
	 *            Amount of players in the game, 2 or 3. Default are 2
	 */
	public void GamServer(int targetPoint, boolean bet, boolean bombs,
			int playerNr) {
		targetPoint = targetPoint;
		playerNr = playerNr == 2 || playerNr == 3 ? playerNr : 2;
		state = new GameState(new Player[playerNr]);
		startNewRound(0);

	}

	/**
	 * Create a new Card stack and distribute the cards to the player.
	 * 
	 * @param payerTurn
	 *            With player start the next round
	 */
	private void startNewRound(int payerTurn) {
		Stack<Card> cardStack = newCards(state.getPlayers().length);
		state = distributeCards(cardStack, state);
		state.newRound();
	}

	/**
	 * Give new cards to the Players. the old cards are not saved.
	 * 
	 * @param cardStack
	 *            A Stack with all cards.
	 * @param state
	 *            The Actual gam state
	 * @return The player have new Cards in the Gamestate
	 */
	private GameState distributeCards(Stack<Card> cardStack, GameState state) {
		Collections.shuffle(cardStack);
		Player[] players = state.getPlayers();
		for (Player p : players) {
			Stack<Card> playerCard = new Stack<Card>();
			for (int i = 0; i < 14; i++) {
				playerCard.push(cardStack.pop());
			}
			for (int i = 0; i < 3; i++) {
				try {
					playerCard.push(new Card(10 + i, Colour.JOKER));
				} catch (IOException e) {
					e.printStackTrace();
					final JOptionPane optionPane = new JOptionPane(
							"An error appeared while distributing the cards. \n Error message:\n"
									+ e.toString(),
							// JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					;
				}// TODO LL dose it crusch?
			}
			p.setCards(playerCard);
		}
		state.setPlayerS(players);
		state.setGamePot(CalculateStackValue(cardStack));
		return state;
	}

	
	/**
	 * Reihenfolge Spieler
	 */
	public GameState logic(GameState playerState, PlayedAction action) {
		//TOTO LL 1. logick definiren; 2. programieren
		GameState ansver = state;
		int playerTurns = playerState.getPlayerTurns();
		
		switch (action) {
		case PASS:
			playerState.setPlayerPlayed(false, playerState.getPlayerTurns());
			int stilPlayingPlayer=0;
			for( boolean plyed :playerState.getPlayerPlayed()){
				stilPlayingPlayer += plyed? 1: 0;
			}
			if (stilPlayingPlayer>1){
				playerState.setPlayerTurns(playerTurns+1);
				ansver= playerState;
			}else{
				playerState.newRound();
			}
			
					
			break;

		case CARDS:
			//TODO  LL nice to double-check the cards on the server
			break;
		}
		
//		if (action == PlayedAction.PASS) {
//			playerState.setPlayerPlayed(false, playerTurns);
//			if (stitchEnd(playerState)) {
////				Player p = playerState.getPlayers()[playerTurns];
////				p.addPoint(playerState.getGamePot());
//			} else {
//			}
//			ansver= playerState; // Hack. Replace an else part over the rest.
//		}

		playerTurns++;
		playerTurns = playerTurns == playerState.getPlayers().length ? 0
				: playerTurns;
		playerState.setPlayerTurns(playerTurns);

		return ansver;

	}

	private boolean stitchEnd(GameState gState) {
		//TODO Javadoc
		int amountOfPased = 0;
		for (boolean played : gState.getPlayerPlayed()) {
			amountOfPased = !played ? amountOfPased++ : amountOfPased;
		}
		boolean ansver = amountOfPased == gState.getPlayers().length - 1 ? true
				: false;
		return ansver;
	}

	/**
	 * 
	 * @param playerAmount
	 *            Amount of paling Person
	 * @return
	 */
	private Stack<Card> newCards(int playerAmount) {
		Stack<Card> cardStack = null;

		return cardStack;
	}
}// end GameServer