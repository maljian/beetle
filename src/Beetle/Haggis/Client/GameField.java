package Beetle.Haggis.Client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Beetle.Haggis.Client.MainView.CombinationWindow;
import Beetle.Haggis.Client.MainView.HaggisMenu;
import Beetle.Haggis.Client.MainView.ProgressWindow;
import Beetle.Haggis.Server.Card;

import javax.swing.JButton;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 * @author Faruk
 * @version 1.0
 * @created 25-Okt-2014 19:32:32
 */
public class GameField extends JFrame {
	
	/**
	 * JButton Selected
	 */

	
	
	/**
	 * Array, Joker
	 */
	private Card btnCard;
	private int btnLay;
	private int btnPass;
	private ArrayList<JButton> JokerCards = new ArrayList<JButton>();
	private ArrayList<JPanel> HandCards = new ArrayList<JPanel>();

	/**
	 * Fix Sizes
	 */
	private int widthCard = 67;
	private int heightCard = 105;

	/**
	 * Opponent
	 */

	private int lblCards;
	private int lblCardsCenter;
	private int lblPlayer;
	public GameFieldModel m_GameFieldModel;
	public final HaggisMenu menuBar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameField frame = new GameField();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GameField() {
		super("Haggis");
		menuBar = new HaggisMenu();
		createFrame();
		getContentPane().setBackground(new Color(178, 34, 34));
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel BottomLine = new JPanel();
		getContentPane().add(BottomLine, BorderLayout.SOUTH);
		BottomLine.setLayout(new BorderLayout(0, 0));

		JPanel ButtonsPlace = new JPanel();
		BottomLine.add(ButtonsPlace, BorderLayout.CENTER);

		JButton btnLegen = new JButton("legen");
		ButtonsPlace.add(btnLegen);

		final JButton btnBube = new JButton();
		Border empty = BorderFactory.createEmptyBorder(1, 1, 1, 1); 
		final Border compound;
	     Color crl = (Color.blue);
	     compound = BorderFactory.createCompoundBorder(empty, new OldRoundedBorderLine(crl));
	     Color crl1 = (Color.red);
	     final Border compound1;
	     compound1 = BorderFactory.createCompoundBorder(empty, new OldRoundedBorderLine(crl1));
	     Color crl2 = (Color.black);
	     final Border compound2;
	     compound2 = BorderFactory.createCompoundBorder(empty, new OldRoundedBorderLine(crl2));
	     
		btnBube.setPreferredSize(new Dimension(widthCard, heightCard));
		btnBube.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == btnBube){
			
			        btnBube.setBorderPainted(true);
			        btnBube.setFocusPainted(false);
			        btnBube.setBorder(compound);
			        btnBube.setHorizontalTextPosition(SwingConstants.CENTER);
			        btnBube.setVerticalTextPosition(SwingConstants.BOTTOM);

			        btnBube.getModel().addChangeListener(new ChangeListener() {

			            @Override
			            public void stateChanged(ChangeEvent e) {
			                ButtonModel model = (ButtonModel) e.getSource();
			                if (model.isRollover()) {
			                    btnBube.setBorder(compound1);
			                } else {
			                    btnBube.setBorder(compound);
			                }
			                if (model.isPressed()) {
			                    btnBube.setBorder(compound2);
			                   
			                    }
			            
						}
			        });
				}
			}
			
		});

		try {
			Image img = ImageIO.read(getClass().getResource(
					"/Beetle/Resources/Bube.jpg"));

			btnBube.setIcon(new ImageIcon(img));

		} catch (IOException ex) {
		}

		JButton btnDame = new JButton();
		btnDame.setPreferredSize(new Dimension(widthCard, heightCard));
		try {
			Image img = ImageIO.read(getClass().getResource(
					"/Beetle/Resources/Dame.jpg"));

			btnDame.setIcon(new ImageIcon(img));

		} catch (IOException ex) {
		}

		JButton btnKonig = new JButton();
		btnKonig.setPreferredSize(new Dimension(widthCard, heightCard));
		try {
			Image img = ImageIO.read(getClass().getResource(
					"/Beetle/Resources/König.jpg"));

			btnKonig.setIcon(new ImageIcon(img));

		} catch (IOException ex) {
		}

		JokerCards.add((JButton) ButtonsPlace.add(btnBube));
		JokerCards.add((JButton) ButtonsPlace.add(btnDame));
		JokerCards.add((JButton) ButtonsPlace.add(btnKonig));

		JButton btnPassen = new JButton("passen");
		btnPassen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		ButtonsPlace.add(btnPassen);

		JPanel CardsPlace = new JPanel();
		BottomLine.add(CardsPlace, BorderLayout.NORTH);
		
		JPanel HelpButtons = new JPanel();
		BottomLine.add(HelpButtons, BorderLayout.WEST);
		HelpButtons.setLayout(new BorderLayout(0, 0));
		
		final JButton btnCombination = new JButton("C");
		btnCombination.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  if (e.getSource() == btnCombination){
					   new CombinationWindow();
				  }
			}
		});
		HelpButtons.add(btnCombination, BorderLayout.WEST);
		
		final JButton btnProgress = new JButton("P");
		btnProgress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  if (e.getSource() == btnProgress){
					   new ProgressWindow();
			}
		}
		});
		HelpButtons.add(btnProgress, BorderLayout.EAST);
		
				JPanel playerpanel1 = new JPanel();
				BottomLine.add(playerpanel1, BorderLayout.EAST);
				FlowLayout fl_playerpanel1 = (FlowLayout) playerpanel1.getLayout();
				fl_playerpanel1.setAlignment(FlowLayout.RIGHT);
				playerpanel1.setBackground(Color.BLACK);
				
				JLabel Player1 = new JLabel("SPIELERNAME");
				playerpanel1.add(Player1);

		JButton cardHand[] = new JButton[15];
		for (int i = 1; i != 15; i++) {
			cardHand[i] = new JButton("card " + i);
			CardsPlace.add(cardHand[i]);

			/**
			 * Tisch (MITTE)
			 */

			JPanel centerField = new JPanel();
			getContentPane().add(centerField, BorderLayout.CENTER);
			centerField.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JButton tableCard[] = new JButton[15];
			for (int j = 1; j != 15; j++) {
				cardHand[j] = new JButton("card " + j);
				centerField.add(cardHand[j]);
/**
 * Tisch (MITTE) beendet
 * Gegner Label
 */
			JPanel opponentField = new JPanel();
			getContentPane().add(opponentField, BorderLayout.NORTH);
			opponentField.setLayout(new BorderLayout(0, 0));

			JPanel opponent_1 = new JPanel();
			opponent_1.setBackground(Color.BLACK);
			FlowLayout fl_opponent_1 = (FlowLayout) opponent_1.getLayout();
			fl_opponent_1.setAlignment(FlowLayout.RIGHT);
			opponentField.add(opponent_1, BorderLayout.WEST);
			
			JLabel Player2 = new JLabel("SPIELERNAME2");
			opponent_1.add(Player2);

			JPanel opponent_2 = new JPanel();
			opponent_2.setBackground(Color.BLACK);
			opponentField.add(opponent_2, BorderLayout.EAST);
			
			JLabel Player3 = new JLabel("SPIELERNAME3");
			opponent_2.add(Player3);
		}
		}

	}

	void createFrame() {

		setJMenuBar(menuBar);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(1200, 700));

	}

	public void EventHandler() {

	}
	class OldRoundedBorderLine extends AbstractBorder {

	    private final static int MARGIN = 5;
	    private static final long serialVersionUID = 1L;
	    private Color color;

	    OldRoundedBorderLine(Color clr) {
	        color = clr;
	    }}
}// end GameField
