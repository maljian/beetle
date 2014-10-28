package Beetle.Haggis.Message;
import Beetle.Haggis.Server.Card;
import Beetle.Haggis.Server.Player;

/**
 * @author 
 * @version 1.0
 * @created 25-Okt-2014 19:32:33
 */
public class GameState {

	private Card lastPlayedCards;
	/**
	 * player [0,1,2], cards[Card]
	 */
	private Player player [];
	private int playerTurns;
	/**
	 * after distributing cards.
	 * player first finish get this point, next gets 0 point.
	 */
	private int restCardValue;

	public GameState(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * >0 false
	 * 1== possible
	 * 2 === higher bomb
	 */
	public int chekKombination(){
		return 0;
	}
}//end GameState