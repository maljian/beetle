/**
 * 
 */
package Beetle.Haggis.Client;

import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import Beetle.Haggis.Server.Card;
import Beetle.Haggis.Server.Card.Colour;

/**
 * @author Marco Mancuso
 *
 */
public class ButtonCard extends JButton {

	private Card card;
	private int widthCard = 67;
	private int heightCard = 105;

	public ButtonCard(Card card) {
		super();
		this.card = card;
		setIcon(new ImageIcon(card.getImage()));
		setPreferredSize(new Dimension(widthCard, heightCard));
		
		
		// Card bub= new Card(6, Colour.GREEN);
		
	}
}
