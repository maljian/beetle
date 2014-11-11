package Beetle.Haggis.Client.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import Beetle.Haggis.Client.MainView.*;

public class HaggisMenu extends JMenuBar {

	
	
	public HaggisMenu(){
		createMenuBar();
		
	}
	
	void createMenuBar(){		
		JMenu menu = new JMenu("Menü");
		add(menu);
		
		JMenuItem rules = new JMenuItem("Regeln");
		menu.add(rules);
		rules.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
			
			}
		});
		
		JMenuItem info = new JMenuItem("Spielinformation");
		menu.add(info);
		
		JMenu setting = new JMenu("Einstellungen");
		add(setting);
	}
	

	
}
