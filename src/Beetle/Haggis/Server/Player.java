package Beetle.Haggis.Server;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Marco Mancuso
 * @version 1.0
 * @created 25-Okt-2014 19:32:34
 */
public class Player {

	private int id;
	private char name;
	private int points;
	private Stack<Card> cards = new Stack<Card> ();

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

	public Stack<Card> getCards() {
		return  cards;
	}

	public void setCards(Stack<Card> playerCard) {
		this.cards = playerCard;
	}
}//end Player