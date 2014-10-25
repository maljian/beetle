package Rest;
import java.net.ServerSocket;

import Server.EventHandlerServer;

/**
 * @author Loïc
 * @version 1.0
 * @created 25-Okt-2014 19:32:33
 */
public class Listener {

	private EventHandlerServer controller;
	private ServerSocket listener;
	private boolean stopThred = false;
	public GameServer m_GameServer;

	public Listener(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param event
	 * @param port
	 */
	public void Listener(EventHandlerServer event, int port){

	}

	/**
	 * Thread
	 */
	public void run(){

	}

	public void stopListenung(){

	}
}//end Listener