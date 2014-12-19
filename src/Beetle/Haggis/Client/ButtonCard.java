/**
 * 
 */
package Beetle.Haggis.Client;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

import Beetle.Haggis.Network.Card;

/**
 * @author Marco Mancuso
 *
 */
public class ButtonCard extends JToggleButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Card card;
	private int widthCard = 67;
	private int heightCard = 105;

	public ButtonCard(Card card) {
		super();
		this.card = card;
		setIcon(new ImageIcon(card.getImage()));
		setBorder(BorderFactory.createEmptyBorder());
		setPreferredSize(new Dimension(widthCard, heightCard));

	}

	public Card getCard() {
		return card;

	}
}