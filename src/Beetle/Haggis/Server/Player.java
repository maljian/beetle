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
	private String name;
	private int points;
	private Stack<Card> cards = new Stack<Card> ();

	public Player(){

	}
	
	public int getId() {
		return id;
	}
	

	public String getName() {
		return name;
	}

/**
 * 
 * @return 
 */
	public int getPoints() {
		return points;
	}

//	public void setPoints(int points) {
//		this.points = points;
//	}
	
	public void addPoint(int newPoints){
		points += newPoints;
	}

	public Stack<Card> getCards() {
		return cards;
	}

	public void setCards(Stack<Card> cards) {
		this.cards = cards;
	}

}//end Player
