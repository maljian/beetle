package Beetle.Haggis.Message;

import java.io.Serializable;

/**
 * @author Nadine Töpfer
 * 
 */
public class Message implements Serializable {


	public enum PlayedAction {
		CARDS, PASS, STATE, WIN
	}

	private static final long serialVersionUID = 1L;
	private GameState gameState;
	private PlayedAction playedAction;

	/**
	 * 
	 * @param gameState
	 * @param playedAction
	 */
	public Message(GameState gameState, 
			PlayedAction playedAction) {
		this.gameState = gameState;
		this.playedAction = playedAction;
	}

	public void newMessage(GameState state) {
		gameState = state;
	}

	public GameState getGameState() {
		return gameState;
	}

	public PlayedAction getPlayedAction() {
		return playedAction;
	}

}