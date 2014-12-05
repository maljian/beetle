package Beetle.Haggis.Client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Beetle.Haggis.Client.NewGameModel;
import Beetle.Haggis.Server.GameServer;

/**
 * @author Faruk
 * @version 1.0
 * @created 25-Okt-2014 19:32:34
 */
public class NewGame extends JoinGame {

	private JPanel contentPane;
	private JTextField txtPlayerName;
	private JButton btnPlay;
	public JTextField txtIpAdress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGame frame = new NewGame();
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
	public NewGame() {
		setTitle("Haggis - Neues Spiel");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JLabel lblPlayerName = new JLabel("Spielername:");
		lblPlayerName.setForeground(new Color(255, 255, 255));
		lblPlayerName.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPlayerName.setBounds(475, 53, 97, 17);
		contentPane.add(lblPlayerName);
		
		JLabel lblNumberPlayer = new JLabel("Anzahl Spieler:");
		lblNumberPlayer.setForeground(new Color(255, 255, 255));
		lblNumberPlayer.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNumberPlayer.setBounds(475, 81, 97, 20);
		contentPane.add(lblNumberPlayer);
		
		btnPlay = new JButton("Spielen");
		btnPlay.setFont(new Font("Arial", Font.PLAIN, 12));
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if (e.getSource() == btnPlay){
					   new GameField().setVisible(true);
					   dispose();
			}
		}});
		btnPlay.setBounds(626, 392, 89, 30);
		contentPane.add(btnPlay);
		
		JComboBox NumberPlayer = new JComboBox();
		NumberPlayer.setModel(new DefaultComboBoxModel(new String[] {"2", "3"}));
		NumberPlayer.setFont(new Font("Arial", Font.PLAIN, 12));
		NumberPlayer.setBounds(684, 81, 33, 20);
		contentPane.add(NumberPlayer);
		
		JLabel lblTargetPoint = new JLabel("Ziel:");
		lblTargetPoint.setForeground(Color.WHITE);
		lblTargetPoint.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTargetPoint.setBounds(475, 112, 97, 20);
		contentPane.add(lblTargetPoint);
		
		JComboBox TargetPoint = new JComboBox();
		TargetPoint.setModel(new DefaultComboBoxModel(new String[] {"10","250", "350"}));
		TargetPoint.setFont(new Font("Arial", Font.PLAIN, 12));
		TargetPoint.setBounds(660, 112, 57, 20);
		contentPane.add(TargetPoint);
		
		JLabel lblIpAdress = new JLabel("IP-Adresse:");
		lblIpAdress.setForeground(Color.WHITE);
		lblIpAdress.setFont(new Font("Arial", Font.PLAIN, 12));
		lblIpAdress.setBounds(475, 143, 97, 20);
		contentPane.add(lblIpAdress);
		
		txtIpAdress = new JTextField();
		txtIpAdress.setHorizontalAlignment(SwingConstants.RIGHT);
		
	

		txtIpAdress.setEditable(false);
		txtIpAdress.setColumns(10);
		txtIpAdress.setBounds(582, 143, 133, 20);
		contentPane.add(txtIpAdress);
		
		JButton btnNewButton = new JButton("kopieren");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
					        new StringSelection(txtIpAdress.getText() ), null
					    );
					}
		});
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setBounds(582, 168, 133, 23);
		contentPane.add(btnNewButton);
		
		final JButton btnZurck = new JButton("Zur\u00FCck");
		btnZurck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if (e.getSource() == btnZurck){
					 //TODO (LL) korekter konstruktor verwenden
					   //new GameServer();
					   dispose();
			}
		}});
		btnZurck.setFont(new Font("Arial", Font.PLAIN, 12));
		btnZurck.setBounds(475, 392, 89, 30);
		contentPane.add(btnZurck);
		
		
	}
}