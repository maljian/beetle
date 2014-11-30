package Beetle.Haggis.Server;

/**
 * @author Marco Mancuso
 * @version 1.0
 * @created 25-Nov-2014 16:51
 */
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Card implements Comparable<Card> {
	public enum Colour {
		RED, YELLOW, ORANGE, GREY, GREEN, JOKER
	}

	// image und imageBack von Datentyp: char --> String
	private Colour colour;
	private BufferedImage image;
	private BufferedImage imageBack;
	private int number;
	private int value;

	/**
	 * KONSTRUKTOR Bsp. Card Karte01 = new Card(8, colour.RED);
	 * 
	 */
	public Card(int number, Colour colour) throws IOException {

		this.number = number;
		this.colour = colour;
		this.value = number % 2;

		// Maximale Kartenmengen
		final int MAXnormaleKarten = 9;

		// StandardPfade
		final String standardPfad = "/Beetle/Resources/";
		String aktuellerPfad = standardPfad;

		// Pfade für Joker und Rückseite
		String rueckseitePfad = standardPfad + "rueckseite.jpg";
		String koenigPfad = standardPfad + "König.jpg";
		String damePfad = standardPfad + "Dame.jpg";
		String bubePfad = standardPfad + "Bube.jpg";

		// Rueckseite Bild
		this.imageBack = ImageIO.read(new File(rueckseitePfad));

		// Zuweisung der Bilder auf den Karten
		// RED, YELLOW, ORANGE, GREY, GREEN, JOKER

		switch (colour) {
		case RED:
			aktuellerPfad += "Red/rot";
			break;

		case YELLOW:
			aktuellerPfad += "Yellow/gelb";
			break;

		case ORANGE:
			aktuellerPfad += "Orange/orange";
			break;

		case GREY:
			aktuellerPfad += "Grey/grau";
			break;

		case GREEN:
			aktuellerPfad += "Green/gruen";
			break;

		case JOKER:
			break;
		}

		if (number <= MAXnormaleKarten) {
			aktuellerPfad += "0";
		}

		aktuellerPfad += number + ".jpg";
		this.image = ImageIO.read(new File(aktuellerPfad));

		// Value Zuweisung der Karten B,D,K;

		switch (number) {
		case 11:
			value = 2;
			this.image = ImageIO.read(new File(bubePfad));
			break;
		case 12:
			value = 3;
			this.image = ImageIO.read(new File(damePfad));
			break;
		case 13:
			value = 5;
			this.image = ImageIO.read(new File(koenigPfad));
			break;
		}
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
		return image;
	}

	public BufferedImage getImageBack() {
		return imageBack;
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

