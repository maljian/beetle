package Beetle.Haggis.Server;

import java.util.ArrayList;

/**
 * @author Marco Mancuso
 * @version 1.0
 * @created 25-Okt-2014 19:32:34
 */
public class Player {

	private int id;
	private char name;
	private int points;
	private ArrayList<Card> cards = new ArrayList<Card> ();

	public Player(){

	}
	
	public int getId() {
		return id;
	}
	

	public char getName() {
		return name;
	}

/**
 * 
 * @return 
 */
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public void finalize() throws Throwable {

	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
}//end Player