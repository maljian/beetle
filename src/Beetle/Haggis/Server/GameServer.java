package Beetle.Haggis.Server;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import javax.swing.JOptionPane;

import Beetle.Haggis.Message.GameState;
import Beetle.Haggis.Message.GameState.Combination;
import Beetle.Haggis.Message.Message.PlayedAction;
import Beetle.Haggis.Server.Card.Colour;

/**
 * @author Loïc Lavanchy
 * @version 1.0
 * @created 14-Nov-2014
 */
public class GameServer implements Remote{ //implements MessageInterface {

	// private EventHandlerServer eventHandler;

	static private GameState state;
	static private int targetPoint;
	static private ArrayList<String> registeredPlayer;
	
//	private SynchronousQueue<Message> sendQueue;
//	private SynchronousQueue<Message> receiveQueue;

	// public ClientConnection m_ClientConnection;
	// public EventHandlerServer m_EventHandlerServer;
	// public Listener m_Listener;

	// private Player[] players;
	// private int targetPoint = 0;
	// public Player m_Player;
	// public Card m_Card;

	// public GameServer() {
	//
	// }

//	/**
//	 * This is a remote method which is executed by the client. It will block
//	 * until there is content in the sendQueue and then pass the message to the
//	 * client.
//	 */
//	public Message receiveMessage() throws RemoteException {
//		Message m = sendQueue.poll();
//		return m;
//	}
//
//	/**
//	 * This is a remote method which is executed by the client. It will receive
//	 * the message and pass it to the receiveQueue where it will then be
//	 * processed by the game logic.
//	 */
//	public void sendMessage(Message m) throws RemoteException {
//		try {
//			receiveQueue.put(m);
//		} catch (InterruptedException e) {
//			sendMessage(m);
//		}
//	}
//
//	public void init() throws RemoteException {
//		// Hier muss sich der Client dann bei irgendwem anmelden.
//		// wichtig ist, dass das Objekt, wo sich der Client anmelden muss,
//		// dieser Klasse hier bekannt ist und über alle Instanzen geshart wird.
//		// D.h. es muss static sein.
//		// Für jede neue Netzwerkverbindung wird ein neues Objekt des Typs
//		// GameServer angelegt, dieses hat alle statischen Felder der Klasse
//		// auch.
//		// Die nichtstatischen Felder, wie z.B. die queues, sind privat für die
//		// entsprechende Netzwerkverbindung.
//
//	}

	/**
	 * 
	 * @author Nadine Töpfer
	 * @param playerName
	 * @return
	 */
	public int initPlayer(String playerName){
		int id;
		if(state.getPlayers().length > registeredPlayer.size()){		// Es werden nur so viel Spieler erstellt, wie angegeben
			
			registeredPlayer.add(playerName);
			id = registeredPlayer.size()-1;
			state.setPlayerName(id, playerName);	
		}else{
			id = -1;
		}
		System.out.println("Player-ID: " + id);
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
	public GameServer(int _targetPoint, boolean bet, boolean bombs, int playerNr) {
		targetPoint = _targetPoint;
		playerNr = playerNr == 2 || playerNr == 3 ? playerNr : 2;
		Player[] players = new Player[playerNr];
		for (int i = 0; i < playerNr; i++) {
			Player p = new Player(i);
			players[i] = p;

		}
		state = new GameState(players);
		startNewRound(0);

	}

	/**
	 * Create a new Card stack and distribute the cards to the player.
	 * 
	 * @param payerTurn
	 *            With player start the next round
	 */
	private void startNewRound(int playerTurn) {
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
	 *            The Actual game state
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
				} catch (Exception e) {
					e.printStackTrace();
					final JOptionPane optionPane = new JOptionPane(
							"An error appeared while distributing the cards. \n Error message:\n"
									+ e.toString(),
							// JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);

				}// TODO LL Test: dose it crusch?
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
	public GameState logic(GameState playerState, PlayedAction action) {
		// TOTO LL 1. logick definiren; 2. programieren
		int playerTurns = playerState.getPlayerTurns();

		switch (action) {
		case PASS:
			playerState.setPlayerPlayed(false, playerState.getPlayerTurns());
			int stilPlayingPlayer = 0;
			for (boolean plyed : playerState.getPlayerPlayed()) {
				stilPlayingPlayer += plyed ? 1 : 0;
			}
			if (stilPlayingPlayer > 1) {
				playerState.setPlayerTurns(playerTurns + 1);

			} else {
				playerState.newRound();
			}

			break;

		case CARDS:
			Player aktuelPlayer = playerState.getPlayers()[playerTurns];
			if (aktuelPlayer.getCards().size() == 0) {
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
					// TODO LL Beetles: Inform game win, end game
				}

				if (remainingPlayer > 1) {
					playerState.setPlayerTurns(playerTurns++);
					return playerState; // Replace the else part to avoid having
										// to many steps in.
				}
				Stack<Card> cards = newCards(1);
				playerState = distributeCards(cards, playerState);
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

			} else {
				playerState.setPlayerTurns(playerTurns + 1);
			}
			// TODO LL Zusatz: nice to double-check the cards on the server
			break;
		}

		// playerState.setPlayerTurns(playerTurns++);

		return playerState;

	}

	/**
	 * 
	 * @param playerAmount
	 *            Amount of paling Person
	 * @return
	 */
	private Stack<Card> newCards(int playerAmount) {
		Stack<Card> cardStack = new Stack<>();
		for (int number = 2; number <= 10; number++) {
			for (int clr = 1; clr <= playerAmount +2; clr++) {
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

}
// end GameServer