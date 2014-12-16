package Beetle.Haggis.Client;

import java.awt.EventQueue;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;

import Beetle.Haggis.Message.GameState;
import Beetle.Haggis.Message.Message;
import Beetle.Haggis.Message.MessageInterface;
import Beetle.Haggis.Message.Message.MessageType;
import Beetle.Haggis.Message.Message.PlayedAction;
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
		playerHasTurn();
	}

	public void actualizeView(GameState gs) {
		view.cardsPlace.removeAll();	// Clear from old items befor draving the new one
		view.centerField.removeAll();
		view.panJoker.removeAll();
		gState = gs;
		Player[] p =   gState.getPlayers();
		System.out.println(p);		// null
		System.out.println(id);
		System.out.println(p[id].getCards());
		Stack<Card> playerHandCards =p[id].getCards();
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
				jokerCards.add((ButtonCard) view.panJoker.add(btnCard));
			} else {
				layedCards.add((ButtonCard) view.cardsPlace.add(btnCard));
			}
			btnCard.addItemListener(view);
			
		}
		
		view.jokerCards=null;
		view.layedCards= null;
		view.revalidate();
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
		
		if (id == gState.getPlayerTurns()){
			playerIsOnTurn = true;
		} else {
			playerIsOnTurn = false;
		}
		view.btnPassen.setEnabled(playerIsOnTurn);

//		view.repaint();
		view.revalidate();
		System.out.println("View up to date");
	}

	/**
	 * zum testen einfach Variable yourTurn auf true oder false setzen false:
	 * spieler ist nicht an der reihe true: spieler ist an der reihe
	 * 
	 * @return false oder true
	 */
	public void playerHasTurn() {

		boolean yourTurn;
		// yourTurn= gState.getPlayerTurns()== cilent.id? true: false;
		yourTurn = true; // TODO 1 hier kommt etwas von Loic, was true oder
							// false
							// zurückgibt
		// zu überprüfen wenn der spieler den neuen Stand erhält.
		view.btnPassen.setEnabled(yourTurn);
		//return yourTurn;
	}

	public void checkCard(ArrayList<Card> selectedCards) {

		boolean combinationConfirmed = GameState
				.checkCombinations(selectedCards);
		view.btnLegen.setEnabled(combinationConfirmed && playerIsOnTurn);
		//TODO 2 knöpfe bereits deaktiviren
	}

	public void layCards() {

		if (gState.setNewCombination(view.cardsToCheck)) {
			gState.setLastPlayedCards(view.cardsToCheck);
			view.cardsToCheck.clear(); // Gespilte carten aus der zuprüfen liste
										// entfernen

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

		// TODO 1 Mesage versenden
		
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
	
	public boolean joinGame(String name, String serverIP){
		client = new Client(gfModel);
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
