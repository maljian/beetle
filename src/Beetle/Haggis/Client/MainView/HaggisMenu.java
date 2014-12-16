package Beetle.Haggis.Client.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Beetle.Haggis.Client.GameFieldModel;
import Beetle.Haggis.Client.StartWindow;

public class HaggisMenu extends JMenuBar {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem menuItemR;
	private JMenuItem menuItemS;
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
		add(menu);
		menu.add(getStart());
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
		return menuItemS;
	}
	
}

