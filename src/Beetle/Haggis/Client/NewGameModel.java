package Beetle.Haggis.Client;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import Beetle.Haggis.Network.EventHandlerServer;

/**
 * 
 * @author Faruk Doganci
 *
 */
public class NewGameModel extends JoinGameModel {

	public static NewGame m_View;
	public GameFieldModel gfModel;

	public String getIpAdress() {
		return ipAdress;
	}

	private EventHandlerServer ehs;
	protected String ipAdress;
	protected String targetP;

	public NewGameModel(GameFieldModel gfModel) {
		super(gfModel);
		this.gfModel = gfModel;
		m_View = new NewGame(this);
		m_View.setVisible(true);

		try {
			String ipv4 = InetAddress.getLocalHost().toString();
			String[] ip = ipv4.split("/");
			m_View.txtIpAdress.setText(ip[1]);
			ipAdress += ip[1];
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author Nadine T�pfer
	 */
	protected void startServer() {
		int numberPlayer = Integer.parseInt(m_View.numberPlayer
				.getSelectedItem().toString());
		int targetPoint = Integer.parseInt(m_View.TargetPoint.getSelectedItem()
				.toString());
		targetP = "" + targetPoint;
		boolean bet = false; // Preparation for future version
		boolean bombs = false; // Preparation for future version
		if (ehs != null){
			ehs.stopServer();
		}
		ehs = new EventHandlerServer();
		gfModel.setEventHandlerServer(ehs);
		try {
			ehs.startServer(targetPoint, bet, bombs, numberPlayer);
			String serverIP = m_View.txtIpAdress.getText();
			String name = m_View.txtPlayerName.getText();
			boolean joined = gfModel.joinGame(name, serverIP);
			if (joined) {
				m_View.dispose();
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane
					.showMessageDialog(
							null,
							"Der Server konnte nicht gestartet werden. Bitte versuchen Sie es erneut.",
							"Information", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void goBack() {
		new StartWindow(gfModel).setVisible(true);
		m_View.dispose();
	}

}
