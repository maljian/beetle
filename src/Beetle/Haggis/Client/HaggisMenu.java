package Beetle.Haggis.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class HaggisMenu extends JMenuBar implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem menuItemR;
	private JMenuItem menuItemS;
	private JMenuItem menuItemE;
	private JMenuItem menuItemI;
	private GameFieldModel gfModel;
	private RulesWindow rw;
	private InformationWindow iw;
	
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
		menu.add(getInfo());
		menu.addSeparator();
		menu.add(getExit());
		JMenu background = new JMenu("Hintergrundfarbe");
		add(background);
		background.add(green);
		background.add(red);
		background.add(purple);
		background.add(black);
		background.add(blue);
		background.add(bordeaux);
		buttonGroup.add(green);
		buttonGroup.add(red);
		buttonGroup.add(purple);
		buttonGroup.add(black);
		buttonGroup.add(blue);
		buttonGroup.add(bordeaux);
		green.addActionListener(this);
		red.addActionListener(this);
		purple.addActionListener(this);
		black.addActionListener(this);
		blue.addActionListener(this);
		bordeaux.addActionListener(this);

	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == green){
			gfModel.setBackgroundColor(gfModel.view.green);
		} 
		if(e.getSource() == red){
			gfModel.setBackgroundColor(gfModel.view.red);
		}
		if(e.getSource() == purple){
			gfModel.setBackgroundColor(gfModel.view.purple);
		}
		if(e.getSource() == black){
			gfModel.setBackgroundColor(gfModel.view.black);
		}
		if(e.getSource() == blue){
			gfModel.setBackgroundColor(gfModel.view.blue);
		}
		if(e.getSource() == bordeaux){
			gfModel.setBackgroundColor(gfModel.view.bordeaux);
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
	
	private JMenuItem getInfo(){
		menuItemI = new JMenuItem ("Spielinformation");
		menuItemI.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if (iw == null){
					iw = new InformationWindow(gfModel.getgState());
				} else {
					iw.dispose();
					iw = null;
				}
			}
		});
		menuItemI.setMnemonic(KeyEvent.VK_I);
		
		return menuItemI;
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
	
	/**
	 * 
	 * @return
	 */
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
	ButtonGroup buttonGroup = new ButtonGroup();
	
	JRadioButtonMenuItem green = new JRadioButtonMenuItem("Grün", true);
	JRadioButtonMenuItem red = new JRadioButtonMenuItem("Rot");
	JRadioButtonMenuItem purple = new JRadioButtonMenuItem("Lila");
	JRadioButtonMenuItem black = new JRadioButtonMenuItem("Schwarz");
	JRadioButtonMenuItem blue = new JRadioButtonMenuItem("Blau");
	JRadioButtonMenuItem bordeaux = new JRadioButtonMenuItem("Bordeaux");
	
}