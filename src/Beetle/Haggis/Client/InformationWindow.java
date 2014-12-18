package Beetle.Haggis.Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Beetle.Haggis.Message.GameState;
import Beetle.Haggis.Server.Player;

/**
 * @author Faruk
 * @version 1.0
 * @created 17-Dez-2014 19:32:33
 */

public class InformationWindow extends JFrame {
	private GameState gState;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	private JPanel logoPanel;
	private JLabel logoLabel;
	private JTextField tfAdresse;
	private JTextField tfAmountPlayer;
	private JTextField tfTargetPoint;

	/**
	 * Create the frame.
	 */
	public InformationWindow(GameState gs) {
		super("Spielinformationen");
		gState = gs;
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(178, 34, 34));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAdresse = new JLabel("IP-Adresse:");
		lblAdresse.setForeground(new Color(255, 255, 255));
		lblAdresse.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAdresse.setBounds(475, 81, 97, 17);
		contentPane.add(lblAdresse);

		JLabel lblAmountPlayer = new JLabel("Anzahl Spieler:");
		lblAmountPlayer.setForeground(new Color(255, 255, 255));
		lblAmountPlayer.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAmountPlayer.setBounds(475, 109, 97, 20);
		contentPane.add(lblAmountPlayer);

		logoPanel = new JPanel();
		logoPanel.setBounds(100, 81, 300, 300);
		contentPane.add(logoPanel);
		logoPanel.setLayout(new BorderLayout(0, 0));

		logoLabel = new JLabel("");
		logoLabel.setIcon(new ImageIcon(getClass().getResource(
				"/Beetle/Resources/Haggis_Logo_v10.jpg")));

		logoPanel.add(logoLabel, BorderLayout.CENTER);

		JLabel lblTargetPoint = new JLabel("Ziel:");
		lblTargetPoint.setForeground(Color.WHITE);
		lblTargetPoint.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTargetPoint.setBounds(475, 140, 97, 20);
		contentPane.add(lblTargetPoint);

		JLabel lblSpieler = new JLabel("Spieler");
		lblSpieler.setForeground(Color.WHITE);
		lblSpieler.setFont(new Font("Arial", Font.BOLD, 12));
		lblSpieler.setBounds(475, 200, 97, 20);
		contentPane.add(lblSpieler);

		JLabel lblPunktzahl = new JLabel("Punktzahl");
		lblPunktzahl.setForeground(Color.WHITE);
		lblPunktzahl.setFont(new Font("Arial", Font.BOLD, 12));
		lblPunktzahl.setBounds(582, 200, 97, 20);
		contentPane.add(lblPunktzahl);

		ArrayList<JLabel> lblPlayers = new ArrayList<JLabel>();

		for (Player p : gState.getPlayers()) {
			JLabel lblPlayer = new JLabel(p.getName());
			lblPlayer.setForeground(Color.WHITE);
			lblPlayer.setFont(new Font("Arial", Font.PLAIN, 12));
			lblPlayer.setBounds(475, 231, 97, 20);
			lblPlayers.add(lblPlayer);
			contentPane.add(lblPlayer);
		}

		tfAmountPlayer = new JTextField();
		tfAmountPlayer.setText(gState.getPlayers().length + "");
		tfAmountPlayer.setEditable(false);
		tfAmountPlayer.setColumns(10);
		tfAmountPlayer.setBounds(582, 110, 160, 20);
		contentPane.add(tfAmountPlayer);
		//
		// tfTargetPoint = new JTextField();
		// tfTargetPoint.setText(NGM.targetP);
		// tfTargetPoint.setEditable(false);
		// tfTargetPoint.setColumns(10);
		// tfTargetPoint.setBounds(582, 141, 160, 20);
		// contentPane.add(tfTargetPoint);

		ArrayList<JLabel> lblPoints = new ArrayList<JLabel>();

		for (Player p : gState.getPlayers()) {
			JLabel lblPoint = new JLabel(p.getPoints()+"");
			lblPoint.setForeground(Color.WHITE);
			lblPoint.setFont(new Font("Arial", Font.PLAIN, 12));
			lblPoint.setBounds(582, 231, 97, 20);
			lblPoints.add(lblPoint);
			contentPane.add(lblPoint);

		}

		setVisible(true);
	}
}
