package Beetle.Haggis.Client;
import java.awt.*;
import javax.swing.*;

/**
 * 
 * @author Faruk
 *
 */
public class RoundButton extends JButton {
  public RoundButton() {
    setPreferredSize(new Dimension(70,70));;
    setBackground(Color.LIGHT_GRAY);
    setContentAreaFilled(false);
    setBorder(null);
    
  }
}