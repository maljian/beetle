package Beetle.Haggis.Client.MainView;

import javafx.scene.layout.Background;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class HaggisMenu extends JMenuBar{
	private Background background = null;
	private JCheckBoxMenuItem menuItemM;
	private JMenuItem menuItemR;

	public HaggisMenu(){
		createMenuBar();
		menuItemR.setEnabled(false);
	}
	
	void createMenuBar(){
		JMenu menu = new JMenu("Menü");
		add(menu);
		
		JMenu setting = new JMenu("Einstellungen");
		add(setting);
		
		JMenu rules = new JMenu("Regeln");
		menu.add(rules);
		
		JMenu information = new JMenu("Spielinformation");
		menu.add(information);
		
		JMenu background = new JMenu("Hintergrund");
		setting.add(background);
	}
	
	
}
