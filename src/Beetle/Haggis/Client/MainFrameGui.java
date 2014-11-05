package Beetle.Haggis.Client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.prism.Image;

import Beetle.Haggis.Client.MainView.HaggisMenu;

/**
 * @author Nadine Töpfer
 * @version 1.0
 * @created 25-Okt-2014 19:32:33
 */
public class MainFrameGui extends JFrame{
	
	private JPanel contentPane;

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

	private MainFrameModel mainFrameModel;
	/**
	 * Depending on where the player is the panel contains either: Startscreen,
	 * NewGame, GameFeald, JoinGam
	 */
	public EventHandlerMainFrame m_EventHandlerMainFrame;
	public MainFrameModel m_MainFrameModel;

	public MainFrameGui(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(200, 200, 900, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Menü");
		menuBar.add(menu);
		
		JMenuItem rules = new JMenuItem("Regeln");
		menu.add(rules);
		
		JMenuItem info = new JMenuItem("Spielinformation");
		menu.add(info);
		
		JMenu setting = new JMenu("Einstellungen");
		menuBar.add(setting);
		
		contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
	}
//	
//	JMenuItem getRules(){
//		JMenuItem rules = new JMenuItem("Regeln");
//		rules.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent arg0){
//				Image image = ImageIO.read(new File(".jpg"));
//			}
//		});
//	}


	public void finalize() throws Throwable {

	}
	
	//TODO event handler
	public void ActionPerformed(){

	}
}//end MainFrameGui