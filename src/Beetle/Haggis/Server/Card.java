package Beetle.Haggis.Server;

/**
 * @author Marco Mancuso
 * @version 1.0
 * @created 25-Okt-2014 19:32:31
 */
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Card {

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
	 * KONSTRUKTOR
	 * Bsp. Card Karte01 = new Card(8, RED);
	 * 
	 */
	public Card(int number, Colour colour) throws IOException {
		
	this.number = number;	
	this.colour = colour;
	this.value = number%2;
	
	// Maximale Kartenmengen
	final int MAXnormaleKarten = 10;
	final int MAXjokerKarten =  3;
	
	//StandardPfade
	String StandardPfad = "Beetle/Resources/";
	
	//Pfade für die Farben
	String RoterPfad = StandardPfad + "Red/rot/";
	String GelberPfad = StandardPfad + "Yellow/gelb/";
	String OrangePfad = StandardPfad + "Orange/orange/";
	String GrauerPfad = StandardPfad + "Grey/grau/";
	String GruenerPfad = StandardPfad + "Green/gruen/";
	
	// Pfade für Joker und Rückseite
	String RueckseitePfad = StandardPfad + "rueckseite.jpg";
	String KoenigPfad = StandardPfad + "König.jpg";
	String DamePfad = StandardPfad + "Dame.jpg";
	String BubePfad = StandardPfad + "Bube.jpg";
	
	// Pfad für die Kartennummer
	String Kartennummer0 = "0" + number + ".jpg";
	String Kartennummer = number + ".jpg";
	
	// Rueckseite Bild
	this.imageBack = ImageIO.read(new File(RueckseitePfad));
	
	
	// Zuweisung der Bilder auf den Karten
	// RED, YELLOW, ORANGE, GREY, GREEN, JOKER
	
	switch(colour){
	case RED:
		
		
		if (number<MAXnormaleKarten){
		this.image = ImageIO.read(new File(RoterPfad + Kartennummer0));
		}else if (number==MAXnormaleKarten){
			this.image = ImageIO.read(new File(RoterPfad + Kartennummer));
		}
		break;
		
		
	case YELLOW:
		if (number<MAXnormaleKarten){
			this.image = ImageIO.read(new File(GelberPfad + Kartennummer0));
			}else if (number==MAXnormaleKarten){
				this.image = ImageIO.read(new File(GelberPfad + Kartennummer));
			}
		break;
		
		
	case ORANGE:
		if (number<MAXnormaleKarten){
			this.image = ImageIO.read(new File(OrangePfad + Kartennummer0));
			}else if (number==MAXnormaleKarten){
				this.image = ImageIO.read(new File(OrangePfad + Kartennummer));
			}
		break;
		
		
	case GREY:
		if (number<MAXnormaleKarten){
			this.image = ImageIO.read(new File(GrauerPfad + Kartennummer0));
			}else if (number==MAXnormaleKarten){
				this.image = ImageIO.read(new File(GrauerPfad + Kartennummer));
			}
		break;
		
		
	case GREEN:
		if (number<MAXnormaleKarten){
			this.image = ImageIO.read(new File(GruenerPfad + Kartennummer0));
			}else if (number==MAXnormaleKarten){
				this.image = ImageIO.read(new File(GruenerPfad + Kartennummer));
			}
		break;
		
		
	case JOKER:
		break;
	}	
	
	// Value Zuweisung der Karten B,D,K;	
	
		switch(number){
		
		case 11:
			value = 2;
			this.image = ImageIO.read(new File(BubePfad));
			break;
		case 12:
			value = 3;
			this.image = ImageIO.read(new File(DamePfad));
			break;
		case 13:
			value = 5;
			this.image = ImageIO.read(new File(KoenigPfad));
			break;
			}
	
	}

	/**
	 * RED, YELLOW, ORANGE, GREY, GREEN, JOKER
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

}// end Card


