package Beetle.Haggis.Client;


import java.awt.BorderLayout;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

	public class InformationWindow extends JFrame {


		/**
		 * Create the frame.
		 */
		public InformationWindow() {
			super("Spielinformationen");
			JLabel lblCombination = new JLabel();
			getContentPane().setLayout(new BorderLayout());

			


			this.setDefaultCloseOperation(CombinationWindow.DISPOSE_ON_CLOSE);
			pack();
			setVisible(true);
			setResizable(false);
		}
		
	}

