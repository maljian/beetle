package Message;
import System.Server.Card;

/**
 * @author Loïc
 * @version 1.0
 * @created 25-Okt-2014 19:32:33
 */
public class GameState {

	private Card lastPlayedCards;
	/**
	 * player [0,1,2], cards[Card]
	 */
	private [int, cards] player;
	private int playerTurns;
	/**
	 * after ditrtibuting cards.
	 * player first finisch get this point, next gets 0 point.
	 */
	private int restCardValue;

	public GameState(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * >0 false
	 * 1== posible
	 * 2 === higer bomb
	 */
	public int chekKombination(){
		return 0;
	}
}//end GameState