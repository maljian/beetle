package Beetle.Haggis.Server;
import java.util.ArrayList;

import Beetle.Haggis.Message.Message;

/**
 * @author 
 * @version 1.0
 * @created 25-Okt-2014 19:32:32
 */
public class EventHandlerServer {

	private ArrayList<ClientConnection> clientList;
	private Listener listener;
	private GameServer main;
	public GameServer m_GameServer;
	public ClientConnection m_ClientConnection;

	public EventHandlerServer(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param clientConection
	 */
	public void addClientConnection(ClientConnection clientConection){

	}

	/**
	 * 
	 * @param messeg
	 * @return 
	 */
	public void broadcast(Message message){

	}

	/**
	 * 
	 * @param client
	 */
	public void removeClient(ClientConnection client){

	}

	private void startServer(){

	}

	private void stopServer(){

	}

	public void updateCilent(){

	}
}//end EventHandlerServer