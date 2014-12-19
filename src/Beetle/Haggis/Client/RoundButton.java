package Beetle.Haggis.Client;
import java.awt.*;
import javax.swing.*;

/**
 * Custom JButton to not repeat it 2 times.
 * @author Faruk Doganci
 *
 */
public class RoundButton extends JButton {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public RoundButton() {
    setPreferredSize(new Dimension(70,70));;
    setBackground(Color.LIGHT_GRAY);
    setContentAreaFilled(false);
    setBorder(null);
    
  }
}