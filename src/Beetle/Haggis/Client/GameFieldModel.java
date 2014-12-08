package Beetle.Haggis.Client;

import java.awt.EventQueue;

import com.sun.glass.ui.View;

import Beetle.Haggis.Server.Card;

/**
 * @author
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

	/**
	 * GameServer
	 */
	public void checkCard() {
		// TODO (NT) Karten hinzufügen → Funktion checkKombination (im
		// GameState) → true - Knopf legen aktivieren → false deaktivieren

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