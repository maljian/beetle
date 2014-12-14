package Beetle.Haggis.Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * @author Faruk
 * @version 1.0
 * @created 25-Okt-2014 19:32:33
 */

public class JoinGame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JTextField txtPlayerName;
	protected JTextField txtIpAdress;
	private JButton btnPlay;
	private JButton btnZurck;
	private JPanel logoPanel;
	private JLabel logoLabel;
	private JoinGameModel model;
	

	/**
	 * Create the frame.
	 */
	public JoinGame(JoinGameModel m) {
		setResizable(false);
		model = m;
		setTitle("Haggis - Spiel beitreten");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(178, 34, 34));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtPlayerName = new JTextField();
		txtPlayerName.setText("Beetle");
		txtPlayerName.setBounds(582, 50, 133, 20);
		contentPane.add(txtPlayerName);
		txtPlayerName.setColumns(10);

		JLabel lblSpielername = new JLabel("Spielername:");
		lblSpielername.setForeground(new Color(255, 255, 255));
		lblSpielername.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSpielername.setBounds(475, 53, 97, 17);
		contentPane.add(lblSpielername);

		JLabel lblBitteIpAdresse = new JLabel("Bitte IP-Adresse eingeben:");
		lblBitteIpAdresse.setForeground(new Color(255, 255, 255));
		lblBitteIpAdresse.setFont(new Font("Arial", Font.PLAIN, 12));
		lblBitteIpAdresse.setBounds(475, 98, 240, 20);
		contentPane.add(lblBitteIpAdresse);

		txtIpAdress = new JTextField();
		txtIpAdress.setColumns(10);
		txtIpAdress.setBounds(475, 129, 240, 20);
		contentPane.add(txtIpAdress);

		logoPanel = new JPanel();
		logoPanel.setBounds(100, 81, 300, 300);
		contentPane.add(logoPanel);
		logoPanel.setLayout(new BorderLayout(0, 0));

		logoLabel = new JLabel("");
		logoLabel.setIcon(new ImageIcon(getClass().getResource(
				"/Beetle/Resources/Haggis_Logo_v6.jpg")));

		logoPanel.add(logoLabel, BorderLayout.CENTER);

		btnPlay = new JButton("Spielen");
		btnPlay.setFont(new Font("Arial", Font.PLAIN, 12));
		btnPlay.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnPlay) {	
					model.joinGame();			
				}
			}
		});
		btnPlay.setBounds(626, 391, 89, 30);
		contentPane.add(btnPlay);

		btnZurck = new JButton("Zur\u00FCck");
		btnZurck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnZurck) {
				model.goBack();
				}
			}
		});
		btnZurck.setFont(new Font("Arial", Font.PLAIN, 12));
		btnZurck.setBounds(475, 391, 89, 30);
		contentPane.add(btnZurck);

	}

	
}
