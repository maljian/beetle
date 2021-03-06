package Beetle.Haggis.Network;

import java.io.Serializable;
import java.util.Stack;

/**
 * @author Marco Mancuso
 * @version 1.0
 * @created 25-Okt-2014 19:32:34
 */
public class Player implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int points;
	private Stack<Card> cards = new Stack<Card> ();
 
	public Player(int id){
		this.id = id;
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

	public void setName(String name2) {
		this.name = name2;			
	}

	public void setId(int id2) {
		this.id = id2;		
	}

	/**
	 * @author Faruk
	 * @return Spielerinformationen
	 */

	@Override
	public String toString() {
		
		String joker = "Joker: \n";
		for (Card c : cards) {
			if (c.getNumber() == 11) {
				joker += "J ";
			} else if (c.getNumber() == 12) {
				joker += "Q ";
			} else if (c.getNumber() == 13) {
				joker += "K ";
			}
		}
		return name +"\n" +"\nAnzahl Karten:\n" + cards.size() + "\nPunkte:\n" + points +"\n"+joker;
	}
	
	
	
	
	
}//end Player
