package Beetle.Haggis.Message;

import java.io.Serializable;
import java.net.Socket;


/**
 * @author Nadine Töpfer
 * @version 1.0
 * @created 25-Okt-2014 19:32:33
 */
public class Message implements Serializable {
	public enum MessageType {
		CONFIRM, ERROR, REGISTER
	}

	public enum PlayedAction {
		CARDS, PASS, STATE
	}
	
	private static final long serialVersionUID = 1L;
	private GameState gameState;
	private MessageType messageType;
	private PlayedAction playedAction;

	public Message(GameState gameState, MessageType messageType, PlayedAction playedAction){
		this.gameState = gameState;
		this.messageType = messageType;
		this.playedAction = playedAction; 
	}

	public GameState getGameState() {
		return gameState;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public PlayedAction getPlayedAction() {
		return playedAction;
	}
	
}// end Message