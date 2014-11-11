package Beetle.Haggis.Client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * @author 	Faruk 
 * @version 1.0
 * @created 25-Okt-2014 19:32:33
 */

public class JoinGame extends JFrame {


	private JPanel contentPane;
	private JTextField txtPlayerName;
	private JTextField txtIpAdress;
	private JButton btnPlay;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JoinGame frame = new JoinGame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JoinGame() {
		setResizable(false);
		setTitle("Haggis - Spiel beitreten");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(178, 34, 34));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtPlayerName = new JTextField();
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
		
		btnPlay = new JButton("Spielen");
		btnPlay.setFont(new Font("Arial", Font.PLAIN, 12));
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPlay.setBounds(626, 391, 89, 30);
		contentPane.add(btnPlay);
		
		
	}
}

