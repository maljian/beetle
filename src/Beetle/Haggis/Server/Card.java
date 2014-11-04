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

	public Card(){

	}

	public void finalize() throws Throwable {

	}

	public Colour getColour() {
		return colour;
	}

	public char getImage() {
		return image;
	}

	public char getImageBack() {
		return imageBack;
	}

	public int getNumber() {
		return number;
	}

	public int getValue() {
		return value;
	}
	
}//end Card