package Beetle.Haggis.Client;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * 
 * @author Nadine Töpfer
 *
 */
public class CombinationWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public CombinationWindow() {
		super("Kombinationen");
		JLabel lblCombination = new JLabel();
		setLayout(new BorderLayout());

		try {
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			InputStream inputCombination = classLoader
					.getResourceAsStream("Beetle/Resources/Kombinationen.jpg");
			Image progress = ImageIO.read(inputCombination);
			lblCombination = new JLabel(new ImageIcon(progress));
			getContentPane().add(lblCombination);
		} catch (IOException e) {

		}

		this.setDefaultCloseOperation(CombinationWindow.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(false);
	}
	
}
