package Beetle.Haggis.Client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;

import Beetle.Haggis.Client.MainView.HaggisMenu;

/**
 * @author Nadine Töpfer
 * @version 1.0
 * @created 25-Okt-2014 19:32:33
 */
public class MainFrameGui extends JFrame{

	private MainFrameGui mainFrameGui;
	private MainFrameModel mainFrameModel;
	/**
	 * Depending on where the player is the panel contains either: Startscreen,
	 * NewGame, GameFeald, JoinGam
	 */
	private Panel mainPanel;
	public EventHandlerMainFrame m_EventHandlerMainFrame;
	public MainFrameModel m_MainFrameModel;
	public Panel m_Panel;

	public final HaggisMenu menuBar;
	
	public MainFrameGui(){
		menuBar = new HaggisMenu();
		
		createFrame();

	}
	
	
	
	void createFrame(){
		setJMenuBar(menuBar);
		
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(m_Panel, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

	public void finalize() throws Throwable {

	}
	
	//TODO event handler
	public void ActionPerformed(){

	}
}//end MainFrameGui