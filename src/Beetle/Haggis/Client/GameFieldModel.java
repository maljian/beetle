package Beetle.Haggis.Client;

import java.awt.EventQueue;

//import com.sun.glass.ui.View;

import java.util.ArrayList;
import java.util.Stack;

import Beetle.Haggis.Message.GameState;
import Beetle.Haggis.Server.Card;

/**
 * @author Marco Mancuso
 * @version 1.0
 * @created 25-Okt-2014 19:32:32
 */
public class GameFieldModel {

	GameFieldModel gfModel;

	private Card cardsCenter;
	/**
	 * Array
	 */
	private Card handCards;
	/**
	 * Array
	 */
	private Card selectedCards;
	private GameField view;

	public GameFieldModel() {
		view = new GameField(this);
		view.setVisible(true);
	}

	
	public void actualizeView(Stack<Card> playerHandCards){
		//for each
		for (Card card : playerHandCards) {
			ButtonCard btnCard = new ButtonCard(card);
			view.JokerCards.clear();
			view.PlayerCards.clear();
			
			if(card.getValue() >= 3){
				view.JokerCards.add((ButtonCard) view.buttonsPlace.add(btnCard));
			}
			else{
				view.PlayerCards.add((ButtonCard) view.cardsPlace.add(btnCard));
			}
			btnCard.addItemListener(view);
		}
	}
	
	
	/**
	 * GameServer
	 */
	public void checkCard(ArrayList<Card> selectedCards) {
		boolean combinationConfirmed = GameState.checkCombinations(view.CardsToCheck);
		view.btnLegen.setEnabled(combinationConfirmed);

	}

	public void layCards() {	
		
		// TODO Karten übergeben (im GameState speichern) und an Message
		// weitergeleitet zum senden
		// mi.sendMessage(m); m = Message
	}

	public void pass() {
		// TODO Message absenden → keine Parameter(View) noetig, aber Message
		// sagen ueberspringen
		// mi.sendMessage(m); m = Message
	}

	// TODO neue Private) funktion knoepfe deaktivieren (passen legen, alenfals
	// cards)

	public void startGame() {
		// viwe = new GameField();

	}

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameFieldModel gfm = new GameFieldModel();
//					GameField frame = new GameField(gfm);
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}// end GameFieldModel