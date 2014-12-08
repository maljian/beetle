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
import Beetle.Haggis.Server.Card.Colour;

import javax.swing.JButton;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JLabel;

/**
 * @author Faruk Doganci, Marco Mancuso
 * @version 1.0
 * @created 25-Okt-2014 19:32:32
 */
public class GameField extends JFrame implements ItemListener{
 
 /**
  * WICHTIG!!!!
  * auskommentierter Bereich ist nicht mehr brauchbar: Eventhandler, img-Aufrufe, usw.
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
 private ArrayList<ButtonCard> JokerCards = new ArrayList<ButtonCard>();
 private ArrayList<ButtonCard> PlayerCards = new ArrayList<ButtonCard>();
 private ArrayList<ButtonCard> OpponentCards = new ArrayList<ButtonCard>();
 private ArrayList<JPanel> HandCards = new ArrayList<JPanel>(); // ?

 /**
  * Fix Sizes
  */
 //private int widthCard = 67;
    //private int heightCard = 105;

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
  
  /** 
   * MM
   * Die Jokerkarten des Spielers
   */
  Card bube = new Card(11, Colour.JOKER);
  Card dame = new Card(12, Colour.JOKER);
  Card koenig = new Card(13, Colour.JOKER); 
  
  
  ButtonCard btnBube = new ButtonCard(bube);
  ButtonCard btnDame = new ButtonCard(dame);
  ButtonCard btnKoenig = new ButtonCard(koenig);
  
  JokerCards.add((ButtonCard) ButtonsPlace.add(btnBube));
  JokerCards.add((ButtonCard) ButtonsPlace.add(btnDame));
  JokerCards.add((ButtonCard) ButtonsPlace.add(btnKoenig));
  
  btnBube.addItemListener(this);
  btnDame.addItemListener(this);
  btnKoenig.addItemListener(this);
  
  /*JButton btnDame = new JButton();
  btnDame.setPreferredSize(new Dimension(widthCard, heightCard));
  try {
   Image img = ImageIO.read(getClass().getResource(
     "/Beetle/Resources/Dame.jpg"));

   btnDame.setIcon(new ImageIcon(img));

  } catch (IOException ex) {
  }   */
 /* Border empty = BorderFactory.createEmptyBorder(1, 1, 1, 1); 
  final Border compound;
      Color crl = (Color.blue);
      compound = BorderFactory.createCompoundBorder(empty, new OldRoundedBorderLine(crl));
      Color crl1 = (Color.red);
      final Border compound1;
      compound1 = BorderFactory.createCompoundBorder(empty, new OldRoundedBorderLine(crl1));
      Color crl2 = (Color.black);
      final Border compound2;
      compound2 = BorderFactory.createCompoundBorder(empty, new OldRoundedBorderLine(crl2));
     */ 
 // btnBube.setPreferredSize(new Dimension(widthCard, heightCard));
 /* btnBube.addActionListener(new ActionListener() {
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
  }); /*
 /* try {
   Image img = ImageIO.read(getClass().getResource(
     "/Beetle/Resources/Bube.jpg"));

   btnBube.setIcon(new ImageIcon(img));

  } catch (IOException ex) {
  }*/
  /* JButton btnKonig = new JButton();
  btnKonig.setPreferredSize(new Dimension(widthCard, heightCard));
  try {
   Image img = ImageIO.read(getClass().getResource(
     "/Beetle/Resources/König.jpg"));

   btnKonig.setIcon(new ImageIcon(img));

  } catch (IOException ex) {
  } */


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
    
    
    
    /**
     * MM
     * Karten des Spielers in der Hand
     * Hier wusste ich nicht woher diese Karten kommen, 
     * als Beispiel sind ein paar zum Testen instanziert
     */
    Card acht = new Card(8, Colour.RED);
    Card neun = new Card(9, Colour.ORANGE);
    ButtonCard btnacht = new ButtonCard(acht);
    ButtonCard btnneun = new ButtonCard(neun);
    btnneun.addItemListener(this);
    CardsPlace.add(btnneun, PlayerCards.add((ButtonCard) ButtonsPlace.add(btnneun))); 
    btnacht.addItemListener(this);
    CardsPlace.add(btnacht, PlayerCards.add((ButtonCard) ButtonsPlace.add(btnacht))); 
    
    
 /* JButton cardHand[] = new JButton[15];
  for (int i = 1; i < 15; i++) {
   cardHand[i] = new JButton("card " + i);
   CardsPlace.add(cardHand[i]);    }    */

   /**
    * Tisch (MITTE)
    */

  JPanel centerField = new JPanel();
   getContentPane().add(centerField, BorderLayout.CENTER);
   centerField.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
   
   
   
   
  /**
   * 
   * Hier kommen die Karten des Gegenspielers
   * OpponentCards ist bereits als Arraylist gespeichert
   * hier muss nur noch mit Card.getImageBack() gefüllt werden, weiß aber nicht (wegen instanzierung der Karten)
   * als kleines Beispiel
   */
   
   
   ButtonCard btntest = new ButtonCard(acht);
   centerField.add(btntest, OpponentCards.add((ButtonCard) ButtonsPlace.add(btntest))); 
   
   
   /* JButton tableCard[] = new JButton[15];
   for (int j = 1; j != 15; j++) {
    tableCard[j] = new JButton("card " + j);
    centerField.add(tableCard[j]);   } */
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

 void createFrame() {

  setJMenuBar(menuBar);
  setExtendedState(JFrame.MAXIMIZED_BOTH); 
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setMinimumSize(new Dimension(1200, 700));

 }

 public void EventHandler() {

 }
 
 
 /*class OldRoundedBorderLine extends AbstractBorder {

     private final static int MARGIN = 5;
     private static final long serialVersionUID = 1L;
     public Color color;

     OldRoundedBorderLine(Color clr) {
         color = clr;
     }} /*


 /*@Override
 public void actionPerformed(ActionEvent e) {
  if(e.getActionCommand() = ActionEvent.ACTION_FIRST)
  switch (ButtonCard.cardSelected) {
  
  case 2:
   ((ButtonCard)e.getSource()).setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
   ButtonCard.cardSelected = 1; 
  
  case 1:
   ((ButtonCard)e.getSource()).setBorder(BorderFactory.createLineBorder(Color.RED, 6));
   ButtonCard.cardSelected = 2;
  }
 }*/
/**
 * ButtonCard hat nun zwei Zustände, Selected und nicht Selected
 * ItemListener für das Verwendet
 * die Border kann noch geändert werden wenn blau nicht passt..
 */
    public void itemStateChanged(ItemEvent e) {
 if(e.getStateChange() == ItemEvent.SELECTED)
    {
  ((ButtonCard)e.getSource()).setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
    }
    else
    {
     
     ((ButtonCard)e.getSource()).setBorder(BorderFactory.createEmptyBorder());
    } 
}

 /*public void actionPerformed(ActionEvent e, ButtonCard z) {
    // TODO Auto-generated method stub
    if (e.getSource() == z){
     z.cardSelected = true;
    }
   } */
 
}// end GameField
  