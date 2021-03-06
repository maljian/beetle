package Beetle.Haggis.Client;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;

import Beetle.Haggis.Message.GameState;
import Beetle.Haggis.Message.GameState.Combination;
import Beetle.Haggis.Message.Message;
//import Beetle.Haggis.Message.Message.MessageType;
import Beetle.Haggis.Message.Message.PlayedAction;
import Beetle.Haggis.Network.Card;
import Beetle.Haggis.Network.Client;
import Beetle.Haggis.Network.EventHandlerServer;
import Beetle.Haggis.Network.Player;

//import com.sun.glass.ui.View;

/**
 * @author Marco Mancuso
 * @version 1.0
 * @created 25-Okt-2014 19:32:32
 */
public class GameFieldModel {

	GameFieldModel gfModel;
	protected GameField view;
	private GameState gState;
	private int id;
	private boolean playerIsOnTurn;
	private Client client;

	public GameFieldModel() {
		view = new GameField(this);
		view.setVisible(true);
		view.btnLegen.setEnabled(false);
		view.btnPassen.setEnabled(false);
	}

	public void actualizeView(GameState gs) {
		// Clear from old items befor draving the new one
		view.panPlayerCards.removeAll();
		view.panCenterField.removeAll();
		view.panJoker.removeAll();
		view.cardsToCheck.clear();
		gState = gs;
		Player[] p = gState.getPlayers();
		String name = gState.getPlayerName(gState.getPlayerTurns());

		gState.setPlayerPlayed(true, id);

		Stack<Card> playerHandCards = p[id].getCards();

		ArrayList<ButtonCard> btnPlayerCards = new ArrayList<ButtonCard>();
		ArrayList<ButtonCard> btnJokerCards = new ArrayList<ButtonCard>();
		ArrayList<ButtonCard> btnPlayedCards = new ArrayList<ButtonCard>();

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

		if (gState.getLastPlayedCards() != null) {
			for (Card card : gState.getLastPlayedCards()) {

				ButtonCard btnCardCenter = new ButtonCard(card);
				btnPlayedCards.add((ButtonCard) view.panCenterField
						.add(btnCardCenter));
			}
		}

		if (id == gState.getPlayerTurns()) {
			playerIsOnTurn = true;
			view.lblHowsTurn.setText("Sie sind am Zug");
		} else {
			
			view.lblHowsTurn.setText(name + " ist am Zug");
			playerIsOnTurn = false;
		}
		
		enableBtn();
		
		updateLabels();
		view.repaint();
		view.revalidate();
	}

	public void anounceWinner(GameState state) {
		int maxPoint = 0;
		int idWithMax = 0;

		for (int i = 0; i < state.getPlayers().length; i++) {
			if (maxPoint < state.getPlayers()[i].getPoints()) {
				maxPoint = state.getPlayers()[i].getPoints();
				idWithMax = i;
			}
		}
		String winnerName = state.getPlayers()[idWithMax].getName();
		view.lblHowsTurn.setText(winnerName + " hat das Spiel mit " + maxPoint
				+ " Punkten gewonnen.");
		view.repaint();
	}

	/**
	 * @author Faruk
	 * @return txt
	 */
	public String updateLabels() {
		String txt = null;
		int opponentTextArea = 2;
		Player[] p = gState.getPlayers();

		for (Player player : p) {

			txt = player.toString();
			if (player.getId() == id) {

				view.player1.setText(txt);
			} else if (opponentTextArea == 2) {
				view.player2.setText(txt);
				opponentTextArea++;
			} else if (opponentTextArea == 3) {
				view.player3.setText(txt);
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
	public void enableBtn() {
		if (playerIsOnTurn == true) {
			view.btnPassen.setEnabled(playerIsOnTurn);
		} else {
			view.btnPassen.setEnabled(playerIsOnTurn);
			view.btnLegen.setEnabled(playerIsOnTurn);
		}
	}

	public void setBackgroundColor(Color bgColor) {

		view.player1.setBackground(bgColor);
		view.player2.setBackground(bgColor);
		view.player3.setBackground(bgColor);
		view.buttonsPlace.setBackground(bgColor);
		view.panPlayerCards.setBackground(bgColor);
		view.panCenterField.setBackground(bgColor);
		view.panJoker.setBackground(bgColor);
		view.helpButtons.setBackground(bgColor);
		view.opponentField.setBackground(bgColor);
	}

	public void checkCard(ArrayList<Card> selectedCards) {

		boolean combinationConfirmed = gState.checkCombinations(selectedCards);
		view.btnLegen.setEnabled(combinationConfirmed && playerIsOnTurn);

	}

	public void layCards() {

		ArrayList<Card> cardsToPlay = view.cardsToCheck;

		if (gState.checkCombinations(cardsToPlay)) {
			if (gState.getCurentCombination() == Combination.NEWTURN) {
				gState.setNewCombination(cardsToPlay);
			}
			gState.setLastPlayedCards((ArrayList<Card>) cardsToPlay.clone());

			Stack<Card> playerCards = gState.getPlayers()[id].getCards();
			for (Card card : cardsToPlay) {
				playerCards.remove(card);
			}
			gState.getPlayers()[id].setCards((Stack<Card>) playerCards.clone());

			Message m = new Message(gState,
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

	}

	public void pass() {
		Message m = new Message(gState, PlayedAction.PASS); // MessageType.CONFIRM,
		client.sendMessage(m);
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

}
