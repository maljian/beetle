package Beetle.Haggis.Client;
import Beetle.Haggis.Message.GameState;
import Beetle.Haggis.Message.Message;
import Beetle.Haggis.Server.GameServer;

/**
 * @author 
 * @version 1.0
 * @created 25-Okt-2014 19:32:33
 */
public class MainFrameModel {

	/**
	 * Gets from server
	 */
	private int id;
	private MainFrameGui mainFrameGui;
	private Message messsage;
	/**
	 * 
	 * Start the Server in a new Thred
	 * Only one of the player gets the server
	 */
	private GameServer server;
	private int socket;
	private GameState state;
	public EventHandlerMainFrame m_EventHandlerMainFrame;
	public MainFrameGui m_MainFrameGui;

	public MainFrameModel(){

	}

	public void finalize() throws Throwable {

	}
	public void connect(){

	}

	public void disconnect(){

	}

	/**
	 * Thred.  Listen to message of the server
	 */
	public void run(){

	}

	public void sendMessage(){

	}
}//end MainFrameModel