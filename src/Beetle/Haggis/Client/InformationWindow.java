package Beetle.Haggis.Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Beetle.Haggis.Client.NewGameModel;
import Beetle.Haggis.Message.GameState;
import java.awt.Font;

public class InformationWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NewGameModel NGM;
	private GameState gState;
	private JPanel panelInfo;

	public InformationWindow() {
		super("Spielinformation");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// setTitle("Spielinformation");
		setBounds(100, 100, 800, 500);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout(0, 0));

		panelInfo = new JPanel();
		getContentPane().add(panelInfo, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 57, 46, 0 };
		gbl_panel.rowHeights = new int[] { 14, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		panelInfo.setLayout(gbl_panel);

		// JLabel lblIpadresse = new JLabel("IP-Adresse:");
		// GridBagConstraints gbc_lblIpadresse = new GridBagConstraints();
		// gbc_lblIpadresse.anchor = GridBagConstraints.NORTHWEST;
		// gbc_lblIpadresse.insets = new Insets(0, 0, 5, 5);
		// gbc_lblIpadresse.gridx = 0;
		// gbc_lblIpadresse.gridy = 0;
		// panelInfo.add(lblIpadresse, gbc_lblIpadresse);

		// JLabel lblGetip = new JLabel(NGM.getIpAdress());
		// GridBagConstraints gbc_lblGetip = new GridBagConstraints();
		// gbc_lblGetip.anchor = GridBagConstraints.WEST;
		// gbc_lblGetip.insets = new Insets(0, 0, 5, 0);
		// gbc_lblGetip.gridx = 1;
		// gbc_lblGetip.gridy = 0;
		// panelInfo.add(lblGetip, gbc_lblGetip);

		JLabel lblNewLabel = new JLabel("Anzahl Spieler:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panelInfo.add(lblNewLabel, gbc_lblNewLabel);

		String playerCount = "";
		try {
			playerCount += gState.getPlayers().length;
		} catch (NullPointerException e) {

		}

		JLabel lblGetnumberplayer = new JLabel(playerCount);
		GridBagConstraints gbc_lblGetnumberplayer = new GridBagConstraints();
		gbc_lblGetnumberplayer.anchor = GridBagConstraints.WEST;
		gbc_lblGetnumberplayer.insets = new Insets(0, 0, 5, 0);
		gbc_lblGetnumberplayer.gridx = 1;
		gbc_lblGetnumberplayer.gridy = 1;
		panelInfo.add(lblGetnumberplayer, gbc_lblGetnumberplayer);

		JLabel lblZiel = new JLabel("Ziel:");
		lblZiel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblZiel.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblZiel = new GridBagConstraints();
		gbc_lblZiel.anchor = GridBagConstraints.WEST;
		gbc_lblZiel.insets = new Insets(0, 0, 5, 5);
		gbc_lblZiel.gridx = 0;
		gbc_lblZiel.gridy = 2;
		panelInfo.add(lblZiel, gbc_lblZiel);

		// JLabel lblGettargetpoint = new JLabel(NGM.targetP);
		// GridBagConstraints gbc_lblGettargetpoint = new GridBagConstraints();
		// gbc_lblGettargetpoint.anchor = GridBagConstraints.WEST;
		// gbc_lblGettargetpoint.insets = new Insets(0, 0, 5, 0);
		// gbc_lblGettargetpoint.gridx = 1;
		// gbc_lblGettargetpoint.gridy = 2;
		// panelInfo.add(lblGettargetpoint, gbc_lblGettargetpoint);

		JLabel lblSpieler_1 = new JLabel("Spieler");
		lblSpieler_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSpieler_1.setBackground(Color.GRAY);
		GridBagConstraints gbc_lblSpieler_1 = new GridBagConstraints();
		gbc_lblSpieler_1.anchor = GridBagConstraints.WEST;
		gbc_lblSpieler_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpieler_1.gridx = 0;
		gbc_lblSpieler_1.gridy = 3;
		panelInfo.add(lblSpieler_1, gbc_lblSpieler_1);

		JLabel lblPunktzahl = new JLabel("Punktzahl");
		lblPunktzahl.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPunktzahl.setBackground(Color.GRAY);
		GridBagConstraints gbc_lblPunktzahl = new GridBagConstraints();
		gbc_lblPunktzahl.anchor = GridBagConstraints.WEST;
		gbc_lblPunktzahl.insets = new Insets(0, 0, 5, 0);
		gbc_lblPunktzahl.gridx = 1;
		gbc_lblPunktzahl.gridy = 3;
		panelInfo.add(lblPunktzahl, gbc_lblPunktzahl);

		JLabel lblSpieler = new JLabel("Spieler1");
		GridBagConstraints gbc_lblSpieler = new GridBagConstraints();
		gbc_lblSpieler.anchor = GridBagConstraints.WEST;
		gbc_lblSpieler.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpieler.gridx = 0;
		gbc_lblSpieler.gridy = 4;
		panelInfo.add(lblSpieler, gbc_lblSpieler);

		JLabel lblGetpoints = new JLabel("getPoints");
		GridBagConstraints gbc_lblGetpoints = new GridBagConstraints();
		gbc_lblGetpoints.anchor = GridBagConstraints.WEST;
		gbc_lblGetpoints.insets = new Insets(0, 0, 5, 0);
		gbc_lblGetpoints.gridx = 1;
		gbc_lblGetpoints.gridy = 4;
		panelInfo.add(lblGetpoints, gbc_lblGetpoints);

		JLabel lblSpieler_2 = new JLabel("Spieler 2");
		GridBagConstraints gbc_lblSpieler_2 = new GridBagConstraints();
		gbc_lblSpieler_2.anchor = GridBagConstraints.WEST;
		gbc_lblSpieler_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpieler_2.gridx = 0;
		gbc_lblSpieler_2.gridy = 5;
		panelInfo.add(lblSpieler_2, gbc_lblSpieler_2);

		JLabel label = new JLabel("getPoints");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 1;
		gbc_label.gridy = 5;
		panelInfo.add(label, gbc_label);

		JLabel lblSpieler_3 = new JLabel("Spieler 3");
		GridBagConstraints gbc_lblSpieler_3 = new GridBagConstraints();
		gbc_lblSpieler_3.anchor = GridBagConstraints.WEST;
		gbc_lblSpieler_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpieler_3.gridx = 0;
		gbc_lblSpieler_3.gridy = 6;
		panelInfo.add(lblSpieler_3, gbc_lblSpieler_3);

		JLabel label_1 = new JLabel("getPoints");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.anchor = GridBagConstraints.WEST;
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 6;
		panelInfo.add(label_1, gbc_label_1);

		setVisible(true);
	}
}
