package Beetle.Haggis.Client;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Beetle.Haggis.Server.Card;

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
		getContentPane().setLayout(null);
		setResizable(false);
		setTitle("Haggis");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		

	}

	public void finalize() throws Throwable {
		super.finalize();
	}
	public void EventHandler(){

	}
}//end GameField