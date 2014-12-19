package Beetle.Haggis.Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Beetle.Haggis.Network.Card;
import Beetle.Haggis.Network.EventHandlerServer;
import Beetle.Haggis.Network.Card.Colour;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

/**
 * @author Faruk Doganci, Marco Mancuso
 * @version 1.0
 * @created 25-Okt-2014 19:32:32
 */
public class GameField extends JFrame implements ItemListener {

	private static final long serialVersionUID = 1L;

	protected Card btnCard;
	protected ArrayList<ButtonCard> jokerCards = new ArrayList<ButtonCard>();
	protected ArrayList<ButtonCard> playerCards = new ArrayList<ButtonCard>();
	protected ArrayList<Card> cardsToCheck = new ArrayList<Card>();
	protected ArrayList<ButtonCard> layedCards = new ArrayList<ButtonCard>();
	protected JPanel buttonsPlace;
	protected JPanel cardsPlayerPlace; 
	protected JPanel cardsJokerPlace;
	protected JPanel opponentField;
	protected JPanel helpButtons;
	protected JPanel cardsLayedPlace;
	protected JPanel panPlayerCards;
	protected JButton btnLegen;
	protected JButton btnPassen;
	protected JPanel panCenterField;
	protected JPanel panJoker;
	protected JPanel playerpanel1;
	protected JPanel playerpanel2;
	protected JPanel playerpanel3;
	protected TextAreaCustom player1;
	protected TextAreaCustom player2;
	protected TextAreaCustom player3;
	protected JLabel lblHowsTurn;
	protected Color green = new Color(0, 100, 0);
	protected Color black = Color.BLACK;
	protected Color purple = new Color(100, 0, 100);
	protected Color red = new Color(195, 0, 0);
	protected Color blue = new Color(0, 0, 120);
	protected Color bordeaux = new Color(178, 34, 34);


	private GameFieldModel gfModel;
	private CombinationWindow cw;
	private ProgressWindow pw;
	private EventHandlerServer ehs;
	

	public final HaggisMenu menuBar;
	

	public GameField(GameFieldModel gfm) {
		super("Haggis");
		gfModel = gfm;

		menuBar = new HaggisMenu(gfm);
		createFrame();
		getContentPane().setBackground(new Color(178, 34, 34));
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel BottomLine = new JPanel();
		getContentPane().add(BottomLine, BorderLayout.SOUTH);
		BottomLine.setLayout(new BorderLayout(0, 0));

		buttonsPlace = new JPanel();
		buttonsPlace.setBackground(green);
		BottomLine.add(buttonsPlace, BorderLayout.CENTER);

		btnPassen = new JButton("passen");
		btnPassen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gfModel.pass();
			}
		});
		buttonsPlace.add(btnPassen);

		panJoker = new JPanel();
		buttonsPlace.add(panJoker);
		panJoker.setBackground(green);
		
		/** 
		 * @author Marco Mancuso
		 *         Joker sollen sowieso angezeigt werden.
		 */
		for (int i = 11; i < 14; i++) {
			Card card = new Card(i, Colour.JOKER);
			ButtonCard btnCard = new ButtonCard(card);
			jokerCards.add((ButtonCard) panJoker.add(btnCard));
			btnCard.addItemListener(this);
		}

		
		btnLegen = new JButton("legen");
		btnLegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnLegen) {
					gfModel.layCards();
				}
			}
		});
		buttonsPlace.add(btnLegen);

		panPlayerCards = new JPanel();
		panPlayerCards.setBackground(green);
		BottomLine.add(panPlayerCards, BorderLayout.NORTH);

		helpButtons = new JPanel();
		helpButtons.setBackground(green);
		BottomLine.add(helpButtons, BorderLayout.WEST);
		helpButtons.setLayout(new FlowLayout());

		final JButton btnCombination = new RoundButton();
		try {
			Image comb = ImageIO.read(getClass().getResource(
					"/Beetle/Resources/IconKombinationen.png"));
			btnCombination.setIcon(new ImageIcon(comb));
		} catch (IOException e1) {
		}
		btnCombination.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnCombination) {
					if (cw == null) {
						cw = new CombinationWindow();
					} else {
						cw.dispose();
						cw = null;
					}
				}
			}
		});
		helpButtons.add(btnCombination, BorderLayout.WEST);

		final JButton btnProgress = new RoundButton();
		try {
			Image prog = ImageIO.read(getClass().getResource(
					"/Beetle/Resources/IconSpielablauf.png"));
			btnProgress.setIcon(new ImageIcon(prog));
		} catch (IOException e1) {

		}

		btnProgress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnProgress) {
					if (pw == null) {
						pw = new ProgressWindow();
					} else {
						pw.dispose();
						pw = null;
					}
				}
			}
		});
		helpButtons.add(btnProgress, BorderLayout.EAST);

		playerpanel1 = new JPanel();
		BottomLine.add(playerpanel1, BorderLayout.EAST);
		FlowLayout fl_playerpanel1 = (FlowLayout) playerpanel1.getLayout();
		fl_playerpanel1.setAlignment(FlowLayout.RIGHT);
		playerpanel1.setBackground(Color.LIGHT_GRAY);

		
		/**
		 * Tisch (MITTE)
		 */
		panCenterField = new JPanel();
		panCenterField.setBackground(green);
		getContentPane().add(panCenterField, BorderLayout.CENTER);
		panCenterField.setLayout(new FlowLayout(FlowLayout.CENTER, 5,
				FlowLayout.CENTER)); // FL.Center, 5,5

		
		/**
		 *  Gegner Label
		 */

		opponentField = new JPanel();
		opponentField.setBackground(green);
		getContentPane().add(opponentField, BorderLayout.NORTH);
		opponentField.setLayout(new BorderLayout(0, 0));

		playerpanel2 = new JPanel();
		playerpanel2.setBackground(Color.LIGHT_GRAY);
		FlowLayout fl_playerpanel2 = (FlowLayout) playerpanel2.getLayout();
		fl_playerpanel2.setAlignment(FlowLayout.RIGHT);
		opponentField.add(playerpanel2, BorderLayout.WEST);

		playerpanel3 = new JPanel();
		playerpanel3.setBackground(Color.LIGHT_GRAY);
		opponentField.add(playerpanel3, BorderLayout.EAST);
		
		player1 = new TextAreaCustom();
		player1.setText("Spieler1\n" + "\nAnzahl Karten:\n" + "\nPunkte:\n" + "\nJoker:\n");
		playerpanel1.add(player1);

		player2 = new TextAreaCustom();
		player2.setText("Spieler2\n" + "\nAnzahl Karten:\n" + "\nPunkte:\n" + "\nJoker:\n");
		playerpanel2.add(player2);
		
		player3 = new TextAreaCustom();
		player3.setText("Spieler3\n" + "\nAnzahl Karten:\n" + "\nPunkte:\n" + "\nJoker:\n");
		playerpanel3.add(player3);
		
		lblHowsTurn = new JLabel("Bitte Spiel erstellen oder Spiel beitreten");
		lblHowsTurn.setHorizontalAlignment(SwingConstants.CENTER);
		opponentField.add(lblHowsTurn, BorderLayout.CENTER);
		lblHowsTurn.setForeground(new Color(255, 255, 255));
		lblHowsTurn.setBackground(new Color(0, 255, 0));
		lblHowsTurn.setFont(new Font("Tahoma", Font.PLAIN, 18));

	}

	void createFrame() {

		setJMenuBar(menuBar);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(1200, 700));
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				onClose();
			}
		});
	}

	private void onClose() {
		try {
			ehs.stopServer();

		} catch (Exception e) {
			
		}
		this.dispose();
	}

	/**
	 * @author Marco Mancuso EventHandler ButtonCard hat nun zwei Zust�nde,
	 *         Selected und nicht Selected ItemListener f�r das Verwendet
	 */
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			((ButtonCard) e.getSource()).setBorder(BorderFactory
					.createLineBorder(Color.BLUE, 3));
			// SELECTED Karten werden in eine ArrayList gespeichert
			cardsToCheck.add((Card) ((ButtonCard) e.getSource()).getCard());
		} else {
			((ButtonCard) e.getSource()).setBorder(BorderFactory
					.createEmptyBorder());
			// Nicht SELECTED wieder aus dem ArrayList entfernt
			cardsToCheck.remove((Card) ((ButtonCard) e.getSource()).getCard());
		}
		gfModel.checkCard(cardsToCheck);
	}

	public void setEventHandlerServer(EventHandlerServer ehs) {
		this.ehs = ehs;
	}
	
	

}
