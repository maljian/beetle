package Beetle.Haggis.Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

//import com.sun.glass.ui.View;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Beetle.Haggis.Message.GameState;
import Beetle.Haggis.Message.Message;
import Beetle.Haggis.Message.GameState.Combination;
import Beetle.Haggis.Message.Message.MessageType;
import Beetle.Haggis.Message.Message.PlayedAction;
import Beetle.Haggis.Server.Card;
import Beetle.Haggis.Server.EventHandlerServer;

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

	public GameFieldModel() {
		view = new GameField(this);
		view.setVisible(true);
		view.btnLegen.setEnabled(false);
		view.btnPassen.setEnabled(false);
		playerHasTurn();
	}

	public void actualizeView(GameState gs) {
		gState = gs;
		Stack<Card> playerHandCards = gState.getPlayers()[id].getCards();
		// for each
//		view.jokerCards.clear();
//		view.playerCards.clear();
		// view.centerField.removeAll();
		ArrayList<ButtonCard> layedCards = new ArrayList<ButtonCard>();
		ArrayList<ButtonCard> jokerCards = new ArrayList<ButtonCard>();
		ArrayList<ButtonCard> playedCards = new ArrayList<ButtonCard>();
		view.layedCards.clear();
		for (Card card : playerHandCards) {
			ButtonCard btnCard = new ButtonCard(card);
			

			if (card.getValue() >= 2) {
				jokerCards.add((ButtonCard) view.buttonsPlace.add(btnCard));
			} else {
				layedCards.add((ButtonCard) view.cardsPlace.add(btnCard));
			}
			btnCard.addItemListener(view);
			
		}
		view.layedCards= layedCards;
		view.jokerCards=jokerCards;
		if (gState.getLastPlayedCards() != null) {
			for (Card card : gState.getLastPlayedCards()) {

				ButtonCard btnCardCenter = new ButtonCard(card);
				layedCards.add(btnCardCenter);
			}
			view.layedCards=layedCards;
		}
		// TODO 1 vie aktualisiren
		// gegenspieler aktualisiren
		// Knöpfe aktualisiren
	}

	/**
	 * zum testen einfach Variable yourTurn auf true oder false setzen false:
	 * spieler ist nicht an der reihe true: spieler ist an der reihe
	 * 
	 * @return false oder true
	 */
	public boolean playerHasTurn() {

		boolean yourTurn;
		// yourTurn= gState.getPlayerTurns()== cilent.id? true: false;
		yourTurn = true; // TODO 1 hier kommt etwas von Loic, was true oder
							// false
							// zurückgibt
		// zu überprüfen wenn der spieler den neuen Stand erhält.
		view.btnPassen.setEnabled(yourTurn);
		return yourTurn;
	}

	public void checkCard(ArrayList<Card> selectedCards) {

		boolean combinationConfirmed = GameState
				.checkCombinations(selectedCards);
		view.btnLegen.setEnabled(combinationConfirmed && this.playerHasTurn());
	}

	public void layCards() {

		if (gState.setNewCombination(view.cardsToCheck)) {
			gState.setLastPlayedCards(view.cardsToCheck);
			view.cardsToCheck.clear(); // Gespilte carten aus der zuprüfen liste
										// entfernen

			Message m = new Message(gState, MessageType.CONFIRM,
					PlayedAction.CARDS);
		} else {
			JOptionPane
					.showMessageDialog(
							null,
							"Es ist nicht möglich die Karten zu legen, überprüfen Sie die Kombination.",
							"Kombinationsfehler",
							JOptionPane.INFORMATION_MESSAGE);

		}

		// TODO 1 Mesage versenden
		// mi.sendMessage(m); m = Message
	}

	public void pass() {
		// TODO 1 Message absenden → keine Parameter(View) noetig, aber Message
		// sagen ueberspringen
		// mi.sendMessage(m); m = Message
		Message m = new Message(gState, MessageType.CONFIRM, PlayedAction.PASS);
	}

	public void startGame() {
		// viwe = new GameField();

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
