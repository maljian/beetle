package Beetle.Haggis.Client;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	protected GameField view;
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
		gState = gs;
		Player[] p = gState.getPlayers();
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
			String name = gState.getPlayerName(gState.getPlayerTurns());
			view.lblHowsTurn.setText(name+ " ist am Zug");
			playerIsOnTurn = false;
		}
		playerHasTurn();
		// view.btnPassen.setEnabled(playerIsOnTurn);

		updateLabels();

		view.revalidate();
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

			txt = player.toString() +jokerTxt(player.getCards());
			if (player.getId() == id) {
				
				view.player1.setText(txt);
			} else if (opponentTextArea == 2) {
				view.player2.setText(txt );
				opponentTextArea++;
			} else if (opponentTextArea == 3) {
				view.player3.setText(txt);
			}
		}
		return txt;
	}
	
	/**
	 * @author FD & MM
	 * @param playerHandCards
	 * @return joker
	 */
	private String jokerTxt(Stack<Card> playerHandCards){
		String joker = "Joker: \n";
		for (Card c : playerHandCards) {
			if (c.getNumber() == 11) {
				joker += "J ";
			} else if (c.getNumber() == 12) {
				joker += "B ";
			} else if (c.getNumber() == 13) {
				joker += "K ";
			}
		}
		
		return joker;
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
	
	
	public void setBackgroundColor(Color bgColor){
		
			view.player1.setBackgroundColor(bgColor);
			view.player2.setBackgroundColor(bgColor);
			view.player3.setBackgroundColor(bgColor);
			view.buttonsPlace.setBackground(bgColor);
			view.cardsPlayerPlace.setBackground(bgColor);
			view.cardsJokerPlace.setBackground(bgColor);
			view.cardsLayedPlace.setBackground(bgColor);
			view.panPlayerCards.setBackground(bgColor);
			view.panCenterField.setBackground(bgColor);
			view.panJoker.setBackground(bgColor);
			
		}

	public void checkCard(ArrayList<Card> selectedCards) {
    
		boolean combinationConfirmed = gState
				.checkCombinations(selectedCards);
		view.btnLegen.setEnabled(combinationConfirmed && playerIsOnTurn);
		// TODO 2 knöpfe bereits deaktiviren
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

	}

	public void pass() {
		Message m = new Message(gState, MessageType.CONFIRM, PlayedAction.PASS);
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
