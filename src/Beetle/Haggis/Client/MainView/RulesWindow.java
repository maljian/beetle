package Beetle.Haggis.Client.MainView;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

public class RulesWindow extends JFrame {

	/**
	 * Launch the application. MAIN METHODE NUR ZUM TESTEN IN DER KLASSE
	 */
	public static void main(String[] args) {
		new RulesWindow();

	}

	/**
	 * Create the frame.
	 */
	public RulesWindow() {
		super("Spielregeln");
		JLabel lblRules = new JLabel();
		setLayout(new BorderLayout());
		JScrollBar vBar = new JScrollBar(JScrollBar.VERTICAL, 30, 40, 0, 300);
		add(vBar, BorderLayout.EAST);

		try {
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			InputStream input = classLoader
					.getResourceAsStream("Beetle/Resources/Spielregeln.png");
			Image rules = ImageIO.read(input);
			lblRules = new JLabel(new ImageIcon(rules));
			lblRules.setBounds(100, 100, 100, 100);
			getContentPane().add(lblRules);
		} catch (IOException e) {

		}

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}


}
