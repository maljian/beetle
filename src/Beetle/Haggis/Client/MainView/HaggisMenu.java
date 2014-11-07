package Beetle.Haggis.Client.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
		
		JMenuItem info = new JMenuItem("Spielinformation");
		menu.add(info);
		
		JMenu setting = new JMenu("Einstellungen");
		add(setting);
	}
	
//	JMenuItem getRules() {
//		JMenuItem rules = new JMenuItem("Regeln");
//		rules.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				getRules();
//			}
//		});
//		return rules;
//	}
	
	


	
}
