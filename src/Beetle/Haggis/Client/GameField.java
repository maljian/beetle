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
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Beetle.Haggis.Client.MainView.CombinationWindow;
import Beetle.Haggis.Client.MainView.HaggisMenu;
import Beetle.Haggis.Client.MainView.ProgressWindow;
import Beetle.Haggis.Server.Card;
import Beetle.Haggis.Server.Card.Colour;

/**
 * @author Faruk Doganci, Marco Mancuso
 * @version 1.0
 * @created 25-Okt-2014 19:32:32
 */
public class GameField extends JFrame implements ItemListener {

	/**
	 * WICHTIG!!!! auskommentierter Bereich ist nicht mehr brauchbar:
	 * Eventhandler, img-Aufrufe, usw.
	 */
	/**
	 * JButton Selected
	 */
	/**
	 * Array, Joker
	 */
	private Card btnCard;
	private int btnLay;
	private int btnPass;
	protected ArrayList<ButtonCard> jokerCards = new ArrayList<ButtonCard>();
	protected ArrayList<ButtonCard> playerCards = new ArrayList<ButtonCard>();
	protected ArrayList<Card> cardsToCheck = new ArrayList<Card>();
	private ArrayList<ButtonCard> layedCards = new ArrayList<ButtonCard>();
	protected JPanel buttonsPlace;
	protected JPanel cardsPlace;
	protected JButton btnLegen;
	protected JButton btnPassen;
	private ArrayList<JPanel> handCards = new ArrayList<JPanel>(); // brauchen wir das überhaupt??
	private GameFieldModel gfModel;
	private CombinationWindow cw;
	private ProgressWindow pw;


	/**
	 * Opponent
	 */

	private int lblCards;
	private int lblCardsCenter;
	private int lblPlayer;
	public GameFieldModel m_GameFieldModel;
	public final HaggisMenu menuBar;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GameFieldModel gfm = new GameFieldModel();
//					GameField frame = new GameField(gfm);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public GameField(GameFieldModel gfm) {
		super("Haggis");
		gfModel = gfm;
		
		menuBar = new HaggisMenu(m_GameFieldModel);
		createFrame();
		getContentPane().setBackground(new Color(178, 34, 34));
		getContentPane().setLayout(new BorderLayout(0, 0));
		

		JPanel BottomLine = new JPanel();
		getContentPane().add(BottomLine, BorderLayout.SOUTH);
		BottomLine.setLayout(new BorderLayout(0, 0));

		buttonsPlace = new JPanel();
		buttonsPlace.setBackground(new Color(0, 100, 0));
		BottomLine.add(buttonsPlace, BorderLayout.CENTER);

		btnLegen = new JButton("legen");
		buttonsPlace.add(btnLegen);
		
		/**
		 * 
		 * @author Marco Mancuso
		 * 
		 * Joker sollen sowieso angezeigt werden.
		 */
		for( int i = 11; i < 14; i++){
			Card card = new Card(i, Colour.JOKER);
			ButtonCard btnCard = new ButtonCard(card);
			jokerCards.add((ButtonCard) buttonsPlace.add(btnCard));
			btnCard.addItemListener(this);
		}
	

		btnPassen = new JButton("passen");
		btnPassen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		buttonsPlace.add(btnPassen);

		cardsPlace = new JPanel();
		cardsPlace.setBackground(new Color(0, 100, 0));
		BottomLine.add(cardsPlace, BorderLayout.NORTH);

		JPanel HelpButtons = new JPanel();
		HelpButtons.setBackground(new Color(0, 100, 0));
		BottomLine.add(HelpButtons, BorderLayout.WEST);
		HelpButtons.setLayout(new FlowLayout());
		

		final JButton btnCombination = new RoundButton();
		try {
			Image comb = ImageIO.read(getClass().getResource("/Beetle/Resources/Kombinationen.png"));
			btnCombination.setIcon(new ImageIcon(comb));
		} catch (IOException e1) {
		}
		btnCombination.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnCombination) { 
					if (cw == null){
					cw = new CombinationWindow();
					}
					else {
						cw.dispose();
						cw = null;
					}
				}
			}
		});
		HelpButtons.add(btnCombination, BorderLayout.WEST);

		final JButton btnProgress = new RoundButton();
		try {
			Image prog = ImageIO.read(getClass().getResource("/Beetle/Resources/Spielablauf.png"));
			btnProgress.setIcon(new ImageIcon(prog));
		} catch (IOException e1) {
		
		}
		
		btnProgress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnProgress) {
					if (pw == null){
						pw = new ProgressWindow();
						}
						else {
							pw.dispose();
							pw = null;
						}
					}
				}
		});
		HelpButtons.add(btnProgress, BorderLayout.EAST);

		JPanel playerpanel1 = new JPanel();
		BottomLine.add(playerpanel1, BorderLayout.EAST);
		FlowLayout fl_playerpanel1 = (FlowLayout) playerpanel1.getLayout();
		fl_playerpanel1.setAlignment(FlowLayout.RIGHT);
		playerpanel1.setBackground(new Color(0, 128, 0));

		JLabel Player1 = new JLabel("SPIELERNAME");
		playerpanel1.add(Player1);

		/**
		 * MM momentan nur als Beispiel sind ein paar zum Testen instanziert
		 */
		Card acht = new Card(8, Colour.RED);
		Card neun = new Card(9, Colour.ORANGE);
		ButtonCard btnacht = new ButtonCard(acht);
		ButtonCard btnneun = new ButtonCard(neun);
		btnneun.addItemListener(this);
		cardsPlace.add(btnneun,
				playerCards.add((ButtonCard) buttonsPlace.add(btnneun)));
		btnacht.addItemListener(this);
		cardsPlace.add(btnacht,
				playerCards.add((ButtonCard) buttonsPlace.add(btnacht)));

		/**
		 * Tisch (MITTE)
		 */

		JPanel centerField = new JPanel();
		centerField.setBackground(new Color(0, 100, 0));
		getContentPane().add(centerField, BorderLayout.CENTER);
		centerField.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		/**
		 * MM
		 * Hier Karten in die Mitte legen.
		 * Hier wieder nur ein beispiel
		 */

		ButtonCard btntest = new ButtonCard(acht);
		centerField.add(btntest,
				layedCards.add((ButtonCard) buttonsPlace.add(btntest)));
		
		/**
		 * Tisch (MITTE) beendet Gegner Label
		 */
		JPanel opponentField = new JPanel();
		opponentField.setBackground(new Color(0, 100, 0));
		getContentPane().add(opponentField, BorderLayout.NORTH);
		opponentField.setLayout(new BorderLayout(0, 0));

		JPanel opponent_1 = new JPanel();
		opponent_1.setBackground(new Color(0, 128, 0));
		FlowLayout fl_opponent_1 = (FlowLayout) opponent_1.getLayout();
		fl_opponent_1.setAlignment(FlowLayout.RIGHT);
		opponentField.add(opponent_1, BorderLayout.WEST);

		JLabel Player2 = new JLabel("SPIELERNAME2");	
		opponent_1.add(Player2);

		JPanel opponent_2 = new JPanel();
		opponent_2.setBackground(new Color(0, 128, 0));
		opponentField.add(opponent_2, BorderLayout.EAST);

		JLabel Player3 = new JLabel("SPIELERNAME3");
		opponent_2.add(Player3);

	}

	void createFrame() {

		setJMenuBar(menuBar);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(1200, 700));

	}

	public void EventHandler() {

	}
	/**
	 * @author Marco Mancuso
	 * EventHandler
	 * ButtonCard hat nun zwei Zustände, Selected und nicht Selected
	 * ItemListener für das Verwendet
	 */
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			((ButtonCard) e.getSource()).setBorder(BorderFactory
					.createLineBorder(Color.BLUE, 3));
			//SELECTED Karten werden in eine ArrayList gespeichert
			cardsToCheck.add((Card) ((ButtonCard) e.getSource()).getCard());
		} else {
			((ButtonCard) e.getSource()).setBorder(BorderFactory
					.createEmptyBorder());
			//Nicht SELECTED wieder aus dem ArrayList entfernt
			cardsToCheck.remove((Card) ((ButtonCard) e.getSource()).getCard());
		}
		gfModel.checkCard(cardsToCheck);
	}
}// end GameField
