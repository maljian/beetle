package Beetle.Haggis.Client;
import Beetle.Haggis.Server.Card;

/**
 * @author Nadine
 * @version 1.0
 * @created 25-Okt-2014 19:32:32
 */
public class GameFieldModel {

	/**
	 * @author Nadine
	 * @version 1.0
	 * @created 25-Okt-2014 19:32:32
	 */
	public class NewGameModel extends JoinGameModel {

		private boolean bet;
		private boolean bomb;
		private int numberPlayer;
		private int targetPoint;
		public NewGame m_NewGame;

		public NewGameModel(){

		}

		public void finalize() throws Throwable {
			super.finalize();
		}
		public void newGame(){

		}
	}//end NewGameModel

	private Card cardsCenter;
	/**
	 * Array
	 */
	private Card handCards;
	/**
	 * Array
	 */
	private Card selectedCards;
	public GameField m_GameField;

	public GameFieldModel(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * GameServer
	 */
	public void checkCard(){

	}

	public void layCards(){

	}

	public void pass(){

	}
}//end GameFieldModel