package Beetle.Haggis.Client;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ProgressWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public ProgressWindow() {
		super("Spielablauf");
		JLabel lblProgress = new JLabel();
		setLayout(new BorderLayout());

		try {
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			InputStream inputProgress = classLoader
					.getResourceAsStream("Beetle/Resources/Ablauf.jpg");
			Image progress = ImageIO.read(inputProgress);
			lblProgress = new JLabel(new ImageIcon(progress));
			getContentPane().add(lblProgress);
		} catch (IOException e) {

		}

		this.setDefaultCloseOperation(ProgressWindow.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(false);
	}


}
