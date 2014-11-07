package Beetle.Haggis.Client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;  // VORLAEUFIG FUER DAS TESTEN, KANN SPAETER AUCH WIEDER WEG
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StartWindow extends JFrame implements ActionListener {
	
	
	/**
	 * @author Marco Mancuso
	 * @version 1.0
	 * @created 07.11.2014, 11:02
	 */

	private JPanel contentPane;
	private JButton btnNewGame;
	private JButton btnJoinGame;
	

	/**
	 * Create the frame.
	 */
	public StartWindow(String title) {
		setResizable(false);
		setTitle("Haggis");
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Button 1 
		btnNewGame = new JButton("Neues Spiel");
		btnNewGame.setBounds(309, 108, 117, 29);
		contentPane.add(btnNewGame);
		btnNewGame.addActionListener(this);
		getContentPane().add(btnNewGame);
		
		// Button 2
		btnJoinGame = new JButton("Spiel beitreten");
		btnJoinGame.setBounds(309, 161, 117, 29);
		contentPane.add(btnJoinGame);
		btnJoinGame.addActionListener(this);
		getContentPane().add(btnJoinGame);
	}

	
	// MOMENTAN NUR MIT TEXTAUSGABE, WEITERLEITUNG AUF ANDERE FRAMES KOMMT SPAETER
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnNewGame){
			Object[] options = { "OK"};
			JOptionPane.showOptionDialog(null, "Hier kommt Neues Spiel - Fenster", "Neues Spiel",

			        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,

			        null, options, options[0]);
		}
		
		if (e.getSource() == btnJoinGame){
			Object[] options = { "OK"};
			JOptionPane.showOptionDialog(null, "Hier kommt Spiel Beitreten - Fenster", "Neues Spiel",

			        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,

			        null, options, options[0]);
		}
	}
	/**
	 * Launch the application.
	 * MAIN METHODE NUR ZUM TESTEN IN DER KLASSE
	 */
	public static void main(String[] args) {
		
		
		StartWindow frame = new StartWindow("Haggis");
		frame.setVisible(true);
			}
}
