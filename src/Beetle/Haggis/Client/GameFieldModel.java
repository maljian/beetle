package Beetle.Haggis.Client;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;

import Beetle.Haggis.Message.GameState;
import Beetle.Haggis.Message.GameState.Combination;
import Beetle.Haggis.Message.Message;
import Beetle.Haggis.Message.Message.MessageType;
import Beetle.Haggis.Message.Message.PlayedAction;
import Beetle.Haggis.Message.MessageInterface;
import Beetle.Haggis.Server.Card;
import Beetle.Haggis.Server.Client;
import Beetle.Haggis.Server.EventHandlerServer;
import Beetle.Haggis.Server.Player;

//import com.sun.glass.ui.View;

/**
 * @author Marco Mancuso
 * @version 1.0
 * @created 25-Okt-2014 19:32:32
 */
public class GameFieldModel {

	GameFieldModel gfModel;
	private GameField view;
	private GameState gState;
	private int id;
	private boolean playerIsOnTurn;
	private MessageInterface messageInterface;
	private Client client;

	public GameFieldModel() {
		view = new GameField(this);
		view.setVisible(true);
		view.btnLegen.setEnabled(false);
		view.btnPassen.setEnabled(false);
		// playerHasTurn();
	}

	public void actualizeView(GameState gs) {
		// Clear from old items befor draving the new one
		view.panPlayerCards.removeAll();
		view.panCenterField.removeAll();
		view.panJoker.removeAll();
		// view.jokerCards = null;
		// view.layedCards = null;
		// view.playerCards=null;

		// System.out.println("anzahl zulezt gespilter karten"+gs.getLastPlayedCards().size());
		gState = gs;
		Player[] p = gState.getPlayers();
		// System.out.println(p); // null
		// System.out.println(id);
		// System.out.println(p[id].getCards());
		Stack<Card> playerHandCards = p[id].getCards();
		// for each
		// view.jokerCards.clear();
		// view.playerCards.clear();
		// view.centerField.removeAll();
		ArrayList<ButtonCard> btnPlayerCards = new ArrayList<ButtonCard>();
		ArrayList<ButtonCard> btnJokerCards = new ArrayList<ButtonCard>();
		ArrayList<ButtonCard> btnPlayedCards = new ArrayList<ButtonCard>();
		// view.layedCards.clear();

		for (Card card : playerHandCards) {
			ButtonCard btnCard = new ButtonCard(card);

			if (card.getValue() >= 2) {
				btnJokerCards.add((ButtonCard) view.panJoker.add(btnCard));
			} else {
				btnPlayerCards.add((ButtonCard) view.panPlayerCards
						.add(btnCard));
			}
			btnCard.addItemListener(view);

		}

		// view.revalidate();
		// view.layedCards.addAll( btnPlayerCards);
		// view.jokerCards.addAll(btnJokerCards);
		if (gState.getLastPlayedCards() != null) {
			for (Card card : gState.getLastPlayedCards()) {

				ButtonCard btnCardCenter = new ButtonCard(card);
				btnPlayedCards.add((ButtonCard) view.panCenterField
						.add(btnCardCenter));
			}
			// view.layedCards = btnPlayedCards;
		}

		if (id == gState.getPlayerTurns()) {
			playerIsOnTurn = true;
		} else {
			playerIsOnTurn = false;
		}
		// view.btnPassen.setEnabled(playerIsOnTurn);

		updateLabels();

		view.revalidate();
	}

	public String updateLabels() {

		String jokers = "Jokers: \n";
		String txt = null;
		int opponentTextArea = 2;
		Player[] p = gState.getPlayers();
		Stack<Card> playerHandCards = p[id].getCards();

		for (Card c : playerHandCards) {
			if (c.getNumber() == 11) {
				jokers += "J ";
			} else if (c.getNumber() == 12) {
				jokers += "B ";
			} else if (c.getNumber() == 13) {
				jokers += "K ";
			}
		}

		for (Player player : p) {

			txt = player.toString();
			if (player.getId() == id) {
				view.player1.setText(txt + jokers);
			} else if (opponentTextArea == 2) {
				view.player2.setText(txt + jokers);
				opponentTextArea++;
			} else if (opponentTextArea == 3) {
				view.player3.setText(txt + jokers);
			}
		}
		return txt;
	}

	/**
	 * zum testen einfach Variable yourTurn auf true oder false setzen false:
	 * spieler ist nicht an der reihe true: spieler ist an der reihe
	 * 
	 * @return false oder true
	 */
	public void playerHasTurn() {
		if (playerIsOnTurn == true) {
			view.btnPassen.setEnabled(playerIsOnTurn);
		} else {
			view.btnPassen.setEnabled(playerIsOnTurn);
			view.btnLegen.setEnabled(playerIsOnTurn);
		}
	}

	public void checkCard(ArrayList<Card> selectedCards) {

		boolean combinationConfirmed = GameState
				.checkCombinations(selectedCards);
		view.btnLegen.setEnabled(combinationConfirmed && playerIsOnTurn);
		// TODO 2 knöpfe bereits deaktiviren
	}

	public void layCards() {

		// TODO 1.1 Carten vom spiler wegnemen, wirds gemacht???
		ArrayList<Card> cardsToPlay = view.cardsToCheck;

		if (GameState.checkCombinations(cardsToPlay)) {
			if (gState.getCurentCombination() == Combination.NEWTURN) {
				gState.setNewCombination(cardsToPlay);
			}
			gState.setLastPlayedCards((ArrayList<Card>) cardsToPlay.clone());

			Stack<Card> playerCards = gState.getPlayers()[id].getCards();
			for (Card card : cardsToPlay) {
				playerCards.remove(card);
			}
			gState.getPlayers()[id].setCards((Stack<Card>) playerCards.clone());

			System.out.println(gState.getLastPlayedCards().size() + " ");
			Message m = new Message(gState, MessageType.CONFIRM,
					PlayedAction.CARDS);
			client.sendMessage(m);
		} else {
			JOptionPane
					.showMessageDialog(
							null,
							"Es ist nicht möglich die Karten zu legen, überprüfen Sie die Kombination.",
							"Kombinationsfehler",
							JOptionPane.INFORMATION_MESSAGE);

		}
		view.cardsToCheck.clear(); // Gespilte carten aus der zuprüfen liste
		// entfernen

	}

	public void pass() {
		// TODO 1 Message absenden → keine Parameter(View) noetig, aber Message
		// sagen ueberspringen
		Message m = new Message(gState, MessageType.CONFIRM, PlayedAction.PASS);
		client.sendMessage(m);
	}

	public void startGame() {
		// viwe = new GameField();

	}

	public boolean joinGame(String name, String serverIP) {
		client = new Client(this);
		client.connect(name, serverIP);
		client.start();
		return true;
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// GameFieldModel gfm =
					new GameFieldModel();
					// GameField frame = new GameField(gfm);
					// frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GameState getgState() {
		return gState;
	}

	public void setgState(GameState gState) {
		this.gState = gState;
	}

	public void setEventHandlerServer(EventHandlerServer ehs) {
		view.setEventHandlerServer(ehs);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}// end GameFieldModel
