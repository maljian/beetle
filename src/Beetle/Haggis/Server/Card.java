package Beetle.Haggis.Server;

/**
 * @author
 * @version 1.0
 * @created 25-Okt-2014 19:32:31
 */
public class Card {
	public enum Colour {
		RED, YELLOW, ORANGE, GREY, GREEN, JOKER
	}

	private Colour colour;
	private char image;
	private char imageBack;
	private int number;
	private int value;

	public Card() {

	}

	public void finalize() throws Throwable {

	}

	/**
	 * RED, YELLOW, ORANGE, GREY, GREEN, JOKER
	 * 
	 * @return
	 */
	public Colour getColour() {
		return colour;
	}

	public char getImage() {
		return image;
	}

	public char getImageBack() {
		return imageBack;
	}

	/**
	 * Number on the Cart
	 * 
	 * @return 2 -10: Normal cards, 11: J, 12: Q, 13:K
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Point on cards, for end evaluation
	 * 
	 * @return Impair: 1p, J: 2p, Q: 3p, K:5p
	 */
	public int getValue() {
		return value;
	}

}// end Card