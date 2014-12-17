package Beetle.Haggis.Client.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.JRadioButtonMenuItem;

import Beetle.Haggis.Client.GameFieldModel;
import Beetle.Haggis.Client.JoinGameModel;
import Beetle.Haggis.Client.NewGameModel;
import Beetle.Haggis.Client.StartWindow;

public class HaggisMenu extends JMenuBar implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem menuItemR;
	private JMenuItem menuItemS;
	private JMenuItem menuItemE;
	private GameFieldModel gfModel;
	private RulesWindow rw;
	
	/**
	 * 
	 * @param gfm
	 */
	public HaggisMenu(GameFieldModel gfm){
		gfModel=gfm;
		createMenuBar();
		
	}
	
	void createMenuBar(){		
		JMenu menu = new JMenu("Menü");
		menu.setMnemonic(KeyEvent.VK_M);
		add(menu);
		menu.add(getStart());
		menu.add(getRules());
		menu.addSeparator();
		menu.add(getExit());
		JMenu background = new JMenu("Hintergrund");
		add(background);
		background.add(green);
		background.add(red);
		background.add(yellow);
		background.add(black);
		background.add(blue);
		directionGroup.add(green);
		directionGroup.add(red);
		directionGroup.add(yellow);
		directionGroup.add(black);
		directionGroup.add(blue);
		green.addActionListener(this);
		red.addActionListener(this);
		yellow.addActionListener(this);
		black.addActionListener(this);
		blue.addActionListener(this);

	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == green){
			
		} 
		if(e.getSource() == red){
			
		}
		if(e.getSource() == yellow){
			
		}
		if(e.getSource() == black){
			
		}
		if(e.getSource() == blue){
			
		}
	}
	
	/**
	 * 
	 * @return
	 */
	private JMenuItem getRules(){
		menuItemR = new JMenuItem("Spielregeln");
		menuItemR.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if (rw == null) {
					rw = new RulesWindow();
				} else {
					rw.dispose();
					rw = null;
				
				}
			}
		});
		menuItemR.setMnemonic(KeyEvent.VK_R);
		
		return menuItemR;
	}
	
	/**
	 * 
	 * @return
	 */
	private JMenuItem getStart(){
		menuItemS = new JMenuItem("Start");
		menuItemS.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				new StartWindow(gfModel).setVisible(true);{
					
				}
			}
		});
		menuItemS.setMnemonic(KeyEvent.VK_S);
		
		return menuItemS;
	}
	
	private JMenuItem getExit(){
		menuItemE = new JMenuItem("Exit");
		menuItemE.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.exit(0);{
					
				}
			}
		});
		menuItemE.setMnemonic(KeyEvent.VK_E);
		return menuItemE;
	}
	
	JMenuItem background = new JMenuItem("Hintergrundfarbe");
	
	ButtonGroup directionGroup = new ButtonGroup();
	
	JRadioButtonMenuItem green = new JRadioButtonMenuItem("Grün", true);
		
	JRadioButtonMenuItem red = new JRadioButtonMenuItem("Rot");
	
	JRadioButtonMenuItem yellow = new JRadioButtonMenuItem("Gelb");
	
	JRadioButtonMenuItem black = new JRadioButtonMenuItem("Schwarz");
	
	JRadioButtonMenuItem blue = new JRadioButtonMenuItem("Blau");
	
	
}