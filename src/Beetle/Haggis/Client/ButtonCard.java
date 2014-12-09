/**
 * 
 */
package Beetle.Haggis.Client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import Beetle.Haggis.Server.Card;
import Beetle.Haggis.Server.Card.Colour;

/**
 * @author Marco Mancuso
 *
 */
public class ButtonCard extends JToggleButton {

private Card card;
private int widthCard = 67;
private int heightCard = 105;
 
 public ButtonCard(Card card) {
  super();
  this.card = card;
  setIcon(new ImageIcon(card.getImage()));
  setBorder(BorderFactory.createEmptyBorder());
  setPreferredSize(new Dimension(widthCard, heightCard));
  // Card sechsGruen = new Card(6, Colour.GREEN);
 }
 public Card getCard() {
	return card;
}
}