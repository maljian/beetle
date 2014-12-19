package Beetle.Haggis.Network;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import com.sun.org.apache.xerces.internal.parsers.CachingParserPool.SynchronizedGrammarPool;

import Beetle.Haggis.Message.GameState;
import Beetle.Haggis.Message.GameState.Combination;
import Beetle.Haggis.Message.Message;
//import Beetle.Haggis.Message.Message.MessageType;
import Beetle.Haggis.Message.Message.PlayedAction;
import Beetle.Haggis.Message.MessageInterface;
import Beetle.Haggis.Network.Card.Colour;

/**
 * @author Loïc Lavanchy
 * @version 1.0
 * @created 14-Nov-2014
 */
public class GameServer implements MessageInterface { // Remote{ // {

	static private GameState state;
	static private int targetPoint;
	static private ArrayList<String> registeredPlayer;
	private Stack<Card> fullCardStack = null;
	private Message message;

	/**
	 * 
	 * @author Nadine Töpfer
	 * @param playerName
	 * @return
	 */
	public int initPlayer(String playerName) {
		int id;
		if (state.getPlayers().length > registeredPlayer.size()) {
			// Es werden nur so viel Spieler erstellt, wie angegeben

			registeredPlayer.add(playerName);
			id = registeredPlayer.size() - 1;
			state.setPlayerName(id, playerName);
			state.versionCounter();
		} else {
			id = -1;
		}
		return id;
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
	 * Constructor
	 * 
	 * @param targetPoint
	 * @param bet
	 *            Inactive, only in the next version.
	 * @param bombs
	 *            Inactive, only in the next version.
	 * @param playerNr
	 *            Amount of players in the game, 2 or 3. Default are 2
	 */
	public GameServer(int targetPoint, boolean bet, boolean bombs, int playerNr) {
		this.targetPoint = targetPoint;
		playerNr = playerNr == 2 || playerNr == 3 ? playerNr : 2;
		Player[] players = new Player[playerNr];

		for (int i = 0; i < playerNr; i++) {
			Player p = new Player(i);
			players[i] = p;

		}
		registeredPlayer = new ArrayList<String>(10);
		state = new GameState(players);
		state.setTargetpoint(targetPoint);
		message = new Message(state,  PlayedAction.CARDS); //
		state = distributeCards(state);
		state.newRound();
		

	}


	/**
	 * Give new cards to the Players. the old cards are not saved.
	 * 
	 * @param cardStack
	 *            A Stack with all cards.
	 * @param state
	 *            The Actual game state
	 * @return The player have new Cards in the Gamestate
	 */
	private GameState distributeCards(GameState state) {

		Stack<Card> cardStack = newCards(state.getPlayers().length);
		Collections.shuffle(cardStack);
		Player[] players = state.getPlayers();
		for (Player p : players) {

			Stack<Card> playerCard = new Stack<Card>();
			for (int i = 0; i < 14; i++) {
				playerCard.push(cardStack.pop());
			}

			for (int i = 0; i < 3; i++) {
				playerCard.push(new Card(11 + i, Colour.JOKER));
			}
			Collections.sort(playerCard);
			p.setCards(playerCard);
		}
		state.setPlayers(players);
		state.setGamePot(CalculateStackValue(cardStack));
		return state;
	}

	/**
	 * Reihenfolge Spieler
	 */
	public void logic(GameState playerState, PlayedAction action) {
		// TOTO LL 1. logick definiren; 2. programieren
		int playerTurns = playerState.getPlayerTurns();
		PlayedAction playedAction = PlayedAction.CARDS;

		switch (action) {
		case PASS:
			playerState.setPlayerPlayed(false, playerState.getPlayerTurns());
			int stilPlayingPlayer = 0;
			for (boolean plyed : playerState.getPlayerPlayed()) {
				stilPlayingPlayer += plyed ? 1 : 0;
			}
			if (stilPlayingPlayer > 1) {
				playerState.nextPlayer();

			} else {
				playerState.newRound();
				
			}
			message.newMessage(playerState);

			break;

		case CARDS:
			// TODO 4 LL Zusatz: nice to double-check the cards on the server
			Player aktuelPlayer = playerState.getPlayers()[playerTurns];
			if (aktuelPlayer.getCards().size() != 0) {
				playerState.setPlayerTurns(playerTurns + 1);
			} else {
				int maxHandcards = 0;
				int remainingPlayer = 0;
				for (Player p : playerState.getPlayers()) {
					maxHandcards = maxHandcards < p.getCards().size() ? p
							.getCards().size() : maxHandcards;
					remainingPlayer += 0 < p.getCards().size() ? 1 : 0;
				}
				aktuelPlayer.addPoint(maxHandcards * 5
						+ playerState.getGamePot());
				playerState.setGamePot(0);

				if (aktuelPlayer.getPoints() >= targetPoint) {
					playedAction = PlayedAction.WIN;
				}

				if (remainingPlayer > 1) {
					playerState.setPlayerTurns(playerTurns++);
					playerState.versionCounter();
					message.newMessage(playerState);
					return; // Replace the else part to avoid having
							// to many steps in.
				}
				playerState = distributeCards(playerState);
				playerState.newRound();
				playerState.setActualCombination(Combination.NEWTURN);
				int minPoint = targetPoint;
				Player[] players = playerState.getPlayers();

				// Set the player with the less points as the next player.
				for (int i = 0; i < players.length; i++) {
					if (players[i].getPoints() <= minPoint) {
						minPoint = players[i].getPoints();
						playerState.setPlayerTurns(i);
					}
				}

			}

			break;
		}
		playerState.versionCounter();
//		System.out.println("GmSe, logic, playersturn: "
//				+ playerState.getPlayerTurns());
		state = playerState;
		// state.setLastPlayedCards(playerState.getLastPlayedCards());
		message = new Message(state,  playedAction); //MessageType.CONFIRM,

		// message.newMessage(playerState);
		// System.out.println("server state version:" + state.getVersion());

	}

	/**
	 * @author Faruk Doganci
	 * @param playerAmount
	 *            Amount of paling Person
	 * @return cardStack
	 */
	private Stack<Card> newCards(int playerAmount) {
		Stack<Card> cardStack = new Stack<>();

		for (int number = 2; number <= 10; number++) {
			for (int clr = 1; clr <= playerAmount + 2; clr++) {
				Colour colour = Colour.RED;

				switch (clr) {
				case 1:
					colour = Colour.RED;
					break;
				case 2:
					colour = Colour.YELLOW;
					break;
				case 3:
					colour = Colour.ORANGE;
					break;
				case 4:
					colour = Colour.GREY;
					break;
				case 5:
					colour = Colour.GREEN;
					break;
				}
				Card c = new Card(number, colour);
				cardStack.add(c);
			}
		}
		Collections.shuffle(cardStack);
		return cardStack;
	}

	// RMI: Remot conection from Client
	@Override
	public Message receiveMessage() throws RemoteException {
		return message;
	}

	@Override
	public void sendMessage(Message m) throws RemoteException {
		GameState pState = m.getGameState();
		logic(pState, m.getPlayedAction());
	}

	@Override
	public int init(String name) throws RemoteException {
		return initPlayer(name);
	}

}
