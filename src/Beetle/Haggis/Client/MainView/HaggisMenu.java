package Beetle.Haggis.Client.MainView;

import javafx.scene.layout.Background;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class HaggisMenu extends JMenuBar{
	private Background background = null;
	private JCheckBoxMenuItem menuItemM;

	public HaggisMenu(){
		createMenuBar();
		
	}
	
	void createMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menu = new JMenu("Menü");
		menuBar.add(menu);
		
		JMenuItem rules = new JMenuItem("Regeln");
		menu.add(rules);
		
		JMenuItem info = new JMenuItem("Spielinformation");
		menu.add(info);
		
		JMenu setting = new JMenu("Einstellungen");
		menuBar.add(setting);
	}
	
	
//	JMenuItem getRules(){
//		JMenuItem rules = new JMenuItem("Regeln");
//		rules.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent arg0){
//				Image image = ImageIO.read(new File(".jpg"));
//			}
//		});
//	}
	
}
