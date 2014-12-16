package Beetle.Haggis.Client.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;




import Beetle.Haggis.Client.GameFieldModel;
import Beetle.Haggis.Client.JoinGameModel;
import Beetle.Haggis.Client.NewGameModel;
import Beetle.Haggis.Client.StartWindow;

public class HaggisMenu extends JMenuBar {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem menuItemR;
	private JMenuItem menuItemS;
	private JMenuItem menuItemJ;
	private JMenuItem menuItemN;
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
		menu.add(getNew());
		menu.add(getJoin());
		menu.add(getRules());
		
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
	
	private JMenuItem getJoin(){
		menuItemJ = new JMenuItem("Join");
		menuItemJ.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				new JoinGameModel(gfModel).setViewVisible(true);{
					
				}
			}
		});
		menuItemJ.setMnemonic(KeyEvent.VK_J);
		
		return menuItemJ;
	}	
	
	private JMenuItem getNew(){
		menuItemN = new JMenuItem("New");
		menuItemN.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				new NewGameModel(gfModel).setViewVisible(true);{
					
				}
			}
		});
		menuItemN.setMnemonic(KeyEvent.VK_N);
		
		return menuItemN;
	}	
	
}

