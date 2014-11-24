package Beetle.Haggis.Client.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import Beetle.Haggis.Client.StartWindow;
import Beetle.Haggis.Client.MainView.*;

public class HaggisMenu extends JMenuBar {
	
	private JMenuItem menuItemR;
	private JMenuItem menuItemS;
	
	public HaggisMenu(){
		createMenuBar();
		
	}
	
	void createMenuBar(){		
		JMenu menu = new JMenu("Menü");
		add(menu);
		menu.add(getStart());
		menu.add(getRules());
		
		JMenuItem info = new JMenuItem("Spielinformation");
		menu.add(info);
		
		JMenu setting = new JMenu("Einstellungen");
		add(setting);
	}
	
	private JMenuItem getRules(){
		menuItemR = new JMenuItem("Spielregeln");
		menuItemR.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				new RulesWindow().setVisible(true);{
				
				}
			}
		});
		return menuItemR;
	}
	
	private JMenuItem getStart(){
		menuItemS = new JMenuItem("Start");
		menuItemS.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				new StartWindow().setVisible(true);{
					
				}
			}
		});
		return menuItemS;
	}
	
}
