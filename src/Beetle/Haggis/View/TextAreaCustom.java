package Beetle.Haggis.View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
/**
 * Custom JTextArea to not repeat it 3 times.
 * @author Faruk Doganci
 *
 */

public class TextAreaCustom extends JTextArea {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Color BackgroundColor = new Color(0, 100, 0);

	public TextAreaCustom() {
        setEditable(false);
        setLineWrap(true);
        setWrapStyleWord(true);
        setBackground(this.BackgroundColor);
        setEditable(false);
        setForeground(Color.WHITE);
		setFont(new Font("Arial", Font.PLAIN, 14));
	}

	public Color getBackgroundColor() {
		return BackgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		BackgroundColor = backgroundColor;
	}

}