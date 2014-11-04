package Beetle.Haggis.Message;

import java.net.Socket;

import Beetle.Haggis.Server.Card;

/**
 * @author 
 * @version 1.0
 * @created 25-Okt-2014 19:32:33
 */
public class Message {
	public enum MessageType {
		CHAT, CONFIRM, ERROR, REGISTER
	}

	public enum PlayedAction {
		CARDS, PASS, STATE
	}
	

	/**
	 * Array
	 */
	private Card card[];
	/**
	 * Pass, State, Cards
	 */
	public GameState gameState;
	/**
	 * Register,Confirm,Error, Update
	 */
	public MessageType messageType;
	private char name;
	private PlayedAction PlayedAction;
	private int playersTurn;
	private int points;
	public PlayedAction m_PlayedAction;
	public GameState m_GameState;

	public void finalize() throws Throwable {

	}

	public MessageType Message() {
		return null;
	}

	public Message receive(Socket s) {
		return null;
	}

	public void send(Socket s) {

	}
}// end Message