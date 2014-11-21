package Beetle.Haggis.Client;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Beetle.Haggis.Server.Card;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author 
 * @version 1.0
 * @created 25-Okt-2014 19:32:32
 */
public class GameField extends JFrame {

	/**
	 * Array, Joker
	 */
	private Card btnCard;
	private int btnLay;
	private int btnPass;
	private ArrayList<JButton> JokerCards = new ArrayList<JButton> ();
	private ArrayList<JPanel> HandCards = new ArrayList<JPanel> ();
	/**
	 * Opponent
	 */
	private int lblCards;
	private int lblCardsCenter;
	private int lblPlayer;
	public GameFieldModel m_GameFieldModel;
	
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

	public GameField(){
		getContentPane().setBackground(new Color(178, 34, 34));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel BottomLine = new JPanel();
		getContentPane().add(BottomLine, BorderLayout.SOUTH);
		BottomLine.setLayout(new BorderLayout(0, 0));
		
		JPanel ButtonsPlace = new JPanel();
		BottomLine.add(ButtonsPlace, BorderLayout.SOUTH);
		
		JButton btnLegen = new JButton("legen");
		ButtonsPlace.add(btnLegen);
	
		
		JButton btnBube = new JButton();
		try{
	    Image img = ImageIO.read(getClass().getResource("/Beetle/Resources/Bube.jpg"));
	    
	    btnBube.setIcon(new ImageIcon(img));
	    
	  } catch (IOException ex) {
	  }
		
		JButton btnDame = new JButton();
		try{
		    Image img = ImageIO.read(getClass().getResource("/Beetle/Resources/Dame.jpg"));
		    
		    btnDame.setIcon(new ImageIcon(img));
		    
		  } catch (IOException ex) {
		  }
		
		
		JButton btnKonig = new JButton();
		try{
		    Image img = ImageIO.read(getClass().getResource("/Beetle/Resources/König.jpg"));
		    
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
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setBackground(Color.BLACK);
		ButtonsPlace.add(panel);
		
		JPanel CardsPlace = new JPanel();
		BottomLine.add(CardsPlace, BorderLayout.NORTH);
		
		JButton cardHand[] = new JButton[11];
		for (int i=1; i!=11 ;i++){
		cardHand[i] = new JButton("card "+i);
		CardsPlace.add(cardHand[i]);
//		cardHand[i].setIcon(new ImageIcon("/Beetle/Resources/Grey/grau02.jpg"));
//		getIconImage();{
//			ImageIcon card[i]Image = new ImageIcon("Resources/Grey/grau02.jpg");
//		try {
//		    Image img = ImageIO.read(getClass().getResource("Resources/Grey"));
//		    card[i].setIcon(new ImageIcon(img));
//		  } catch (IOException ex) {
//		  }

		JPanel centerField = new JPanel();
		getContentPane().add(centerField, BorderLayout.CENTER);
		centerField.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel cardField_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) cardField_1.getLayout();
		centerField.add(cardField_1);
		
		JPanel cardField_2 = new JPanel();
		centerField.add(cardField_2);
		
		JPanel cardField_3 = new JPanel();
		centerField.add(cardField_3);
		
		JPanel cardField_4 = new JPanel();
		centerField.add(cardField_4);
		
		JPanel opponentField = new JPanel();
		getContentPane().add(opponentField, BorderLayout.NORTH);
		opponentField.setLayout(new BorderLayout(0, 0));
		
		JPanel opponent_1 = new JPanel();
		opponent_1.setBackground(Color.BLACK);
		FlowLayout fl_opponent_1 = (FlowLayout) opponent_1.getLayout();
		fl_opponent_1.setAlignment(FlowLayout.RIGHT);
		opponentField.add(opponent_1, BorderLayout.WEST);
		
		JPanel opponent_2 = new JPanel();
		opponent_2.setBackground(Color.BLACK);
		opponentField.add(opponent_2, BorderLayout.EAST);
		setTitle("Haggis");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);}
		

	}

	public void EventHandler(){

	}
}//end GameField