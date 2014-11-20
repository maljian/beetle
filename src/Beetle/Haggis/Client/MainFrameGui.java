package Beetle.Haggis.Client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Beetle.Haggis.Client.MainView.HaggisMenu;

/**
 * @author Nadine Töpfer
 * @version 1.0
 * @created 25-Okt-2014 19:32:33
 */
public class MainFrameGui extends JFrame{
	
	/**
	 * Depending on where the player is the panel contains either: Startscreen,
	 * NewGame, GameFeald, JoinGam
	 */
	public EventHandlerMainFrame m_EventHandlerMainFrame;
	public MainFrameModel m_MainFrameModel;
	private MainFrameModel mainFrameModel;
	
	
	public final HaggisMenu menuBar;

	public MainFrameGui(){
		super("Haggis");
		menuBar = new HaggisMenu();
		
		createFrame();
	}
	
	void createFrame(){
		
		setJMenuBar(menuBar);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();	// get size of the screen
		setBounds(0, 0, dim.width, dim.height);							// set window to the right size for the screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}

	//TODO event handler
	public void ActionPerformed(){

	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrameGui frame = new MainFrameGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}//end MainFrameGui