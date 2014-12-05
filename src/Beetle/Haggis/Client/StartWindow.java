package Beetle.Haggis.Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;  // VORLAEUFIG FUER DAS TESTEN, KANN SPAETER AUCH WIEDER WEG
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class StartWindow extends JFrame implements ActionListener {
 
 
 /**
  * @author Marco Mancuso
  * @version 1.0
  * @created 07.11.2014, 11:02
  */

 private JPanel contentPane;
 private JButton btnNewGame;
 private JButton btnJoinGame;
 private JPanel logopanel;
 private JLabel logolabel;

 /**
  * Create the frame.
  */
 public StartWindow() {
	super("Haggis");
  setResizable(false);
  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  setBounds(100, 100, 800, 500);
  contentPane = new JPanel();
  contentPane.setBackground(new Color(178, 34, 34));
  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
  setContentPane(contentPane);
  contentPane.setLayout(null);
  
  // Button 1 
  btnNewGame = new JButton("Neues Spiel");
  btnNewGame.setBounds(540, 81, 117, 29);
  contentPane.add(btnNewGame);
  btnNewGame.addActionListener(this);
  getContentPane().add(btnNewGame);
  
  // Button 2
  btnJoinGame = new JButton("Spiel beitreten");
  btnJoinGame.setBounds(540, 161, 117, 29);
  contentPane.add(btnJoinGame);
  btnJoinGame.addActionListener(this);
  getContentPane().add(btnJoinGame);
  
  logopanel = new JPanel();
  logopanel.setBounds(100, 81, 300, 300);
  contentPane.add(logopanel);
  logopanel.setLayout(new BorderLayout(0, 0));
  
  logolabel = new JLabel("");
  logolabel.setIcon(new ImageIcon(getClass().getResource("/Beetle/Resources/Haggis_Logo_v6.jpg")));
 
  
  logopanel.add(logolabel, BorderLayout.CENTER);
  
 }
 
 @Override
 public void actionPerformed(ActionEvent e) {
  // TODO Auto-generated method stub
  if (e.getSource() == btnNewGame){
   new NewGameModel();
   dispose();

  }
  
  if (e.getSource() == btnJoinGame){
   new JoinGame().setVisible(true);
   dispose();
   }
 }
 /**
  * Launch the application.
  * MAIN METHODE NUR ZUM TESTEN IN DER KLASSE
  */
 public static void main(String[] args) {
  
  StartWindow frame = new StartWindow();
  frame.setVisible(true);
   }
}