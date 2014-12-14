package Beetle.Haggis.Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

// VORLAEUFIG FUER DAS TESTEN, KANN SPAETER AUCH WIEDER WEG

public class StartWindow extends JFrame implements ActionListener {

	
	/**
	 * @author Marco Mancuso (Buttons)
	 * @author Faruk Doganci (EventHandler, Layout (Design), Logo)
	 * @version 1.0
	 * @created 07.11.2014, 11:02
	 */

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewGame;
	private JButton btnJoinGame;
	private JPanel logopanel;
	private JLabel logolabel;
	private GameFieldModel gfModel;

	/**
	 * Create the frame.
	 */
	public StartWindow(GameFieldModel gfm) {
		super("Haggis");
		gfModel = gfm;

		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(178, 34, 34));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Button 1
		btnNewGame = new JButton("Neues Spiel");
		btnNewGame.setBounds(540, 81, 117, 29);
		contentPane.add(btnNewGame);
		btnNewGame.addActionListener(this);
		getContentPane().add(btnNewGame);

		// Button 2
		btnJoinGame = new JButton("Spiel beitreten");
		btnJoinGame.setBounds(540, 161, 117, 29);
		contentPane.add(btnJoinGame);
		btnJoinGame.addActionListener(this);
		getContentPane().add(btnJoinGame);

		logopanel = new JPanel();
		logopanel.setBounds(100, 81, 300, 300);
		contentPane.add(logopanel);
		logopanel.setLayout(new BorderLayout(0, 0));

		logolabel = new JLabel("");
		logolabel.setIcon(new ImageIcon(getClass().getResource(
				"/Beetle/Resources/Haggis_Logo_v6.jpg")));

		logopanel.add(logolabel, BorderLayout.CENTER);

	}

	/**
	 * @author Faruk Doganci EventHandler
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewGame) {
			new NewGameModel(gfModel);
			dispose();
		} else if (e.getSource() == btnJoinGame) {
			new JoinGameModel(gfModel).setViewVisible(true);
			dispose();
		}
	}
}
