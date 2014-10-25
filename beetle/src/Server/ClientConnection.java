package Server;
import java.net.Socket;

import Rest.GameServer;

/**
 * @author Loïc
 * @version 1.0
 * @created 25-Okt-2014 19:32:31
 */
public class ClientConnection {

	private EventHandlerServer controller;
	private int id;
	private Socket socket;
	public EventHandlerServer m_EventHandlerServer;
	public GameServer m_GameServer;

	public ClientConnection(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param socket
	 * @param handler
	 */
	public void ClientConnection(Socket socket, EventHandlerServer handler){

	}

	/**
	 * close al conection
	 */
	public void close(){

	}

	/**
	 * 
	 * @param object
	 */
	public boolean equals(Object object){
		return false;
	}

	/**
	 * close al conection
	 */
	public Socket getSocket(){
		return null;
	}

	/**
	 * Thred run
	 */
	public void run(){

	}
}//end ClientConnection