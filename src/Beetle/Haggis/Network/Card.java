package Beetle.Haggis.Network;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

/**
 * @author Marco Mancuso
 * @version 1.0
 * @created 25-Nov-2014 16:51
 */
public class Card implements Comparable<Card>, Serializable {

	private static final long serialVersionUID = 1L;

	public enum Colour {
		RED, YELLOW, ORANGE, GREY, GREEN, JOKER
	}

	private Colour colour;
	private String imigePath;
	private String imigeBackPath;
	private int number;
	private int value;

	/**
	 * KONSTRUKTOR Bsp. Card Karte01 = new Card(8, colour.RED);
	 * 
	 */
	public Card(int number, Colour colour) {

		this.number = number;
		this.colour = colour;
		this.value = number % 2;

		// Maximale Kartenmengen
		final int maxNormalCards = 9;

		// StandardPfade
		final String standardPath = "/Beetle/Resources/";
		String actualPath = standardPath;

		imigeBackPath = standardPath + "rueckseite.jpg";

		// Zuweisung der Bilder auf den Karten
		switch (colour) {
		case RED:
			actualPath += "Red/rot";
			break;
		case YELLOW:
			actualPath += "Yellow/gelb";
			break;
		case ORANGE:
			actualPath += "Orange/orange";
			break;
		case GREY:
			actualPath += "Grey/grau";
			break;
		case GREEN:
			actualPath += "Green/gruen";
			break;
		case JOKER:
			break;
		}

		if (number <= maxNormalCards) {
			actualPath += "0";
		}
		actualPath += number + ".jpg";

		// Value Zuweisung der Karten B,D,K;
		switch (number) {
		case 11:
			value = 2;
			actualPath = standardPath + "Bube.jpg";
			break;
		case 12:
			value = 3;
			actualPath = standardPath + "Dame.jpg";
			break;
		case 13:
			value = 5;
			actualPath = standardPath + "König.jpg";
			break;
		}
		imigePath = actualPath;

	}

	/**
	 * RED, YELLOW, ORANGE, GREY, GREEN, JOKER
	 * 
	 * @return
	 */
	public Colour getColour() {
		return colour;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public BufferedImage getImage() {
		try {
			return ImageIO.read(getClass().getResource(imigePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public BufferedImage getImageBack() {
		try {
			return ImageIO.read(getClass().getResource(imigeBackPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Number on the Cart
	 * 
	 * @return 2 - 10: Normal cards; 11: J, 12: Q, 13:K
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

	// Comparator
	public int compareTo(Card c) {
		int answer = number - c.getNumber();

		if (answer == 0) {
			answer = colour.compareTo(c.getColour());
		}
		return answer;
	}

}