package Beetle.Haggis.Client;

import java.awt.Color;

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

	public TextAreaCustom() {
        setEditable(false);
        setLineWrap(true);
        setWrapStyleWord(true);
        setBackground(new Color(0, 128, 0));
        setEditable(false);
		
	}

}