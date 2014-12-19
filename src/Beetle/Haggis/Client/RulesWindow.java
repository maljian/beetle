package Beetle.Haggis.Client;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * 
 * @author Nadine Töpfer
 *
 */
public class RulesWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	JLabel lblRules = new JLabel();
	
	/**
	 * Create the frame.
	 */
	public RulesWindow() {
		super("Spielregeln");
		
		setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		JScrollPane vBar = new JScrollPane(panel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		try {
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			InputStream inputRules = classLoader
					.getResourceAsStream("Beetle/Resources/Spielregeln.png");
			Image rules = ImageIO.read(inputRules);
			lblRules = new JLabel(new ImageIcon(rules));
			panel.add(lblRules);
		} catch (IOException e) {

		}

		add(vBar);
		this.setDefaultCloseOperation(RulesWindow.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
		setSize(1260, 660);
		setResizable(false);
	}

}
