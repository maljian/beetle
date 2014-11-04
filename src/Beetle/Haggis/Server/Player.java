package Beetle.Haggis.Server;

/**
 * @author Marco Mancuso
 * @version 1.0
 * @created 25-Okt-2014 19:32:34
 */
public class Player {

	private int id;
	private char name;
	private int points;
	private Card cards[];

	public Player(){

	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public char getName() {
		return name;
	}

	public void setName(char name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Card[] getCards() {
		return cards;
	}

	public void setCards(Card[] cards) {
		this.cards = cards;
	}
	
	public void finalize() throws Throwable {

	}
}//end Player