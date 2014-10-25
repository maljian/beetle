package Message;
import System.Server.Card;

/**
 * @author Loïc
 * @version 1.0
 * @created 25-Okt-2014 19:32:33
 */
public class Message {

	/**
	 * @author Nadine
	 * @version 1.0
	 * @created 25-Okt-2014 19:32:33
	 */
	public class MessageType {

		public MessageTyp Chat;
		public MessageTyp Confirm;
		public MessageTyp Error;
		public MessageTyp Register;
		public Message m_Message;



		public void finalize() throws Throwable {

		}
		public MessageType(){

		}
	}//end MessageType

	/**
	 * Array
	 */
	private Card card;
	/**
	 * Pass, State, Cards
	 */
	public GameState gameState;
	/**
	 * Register,Confirm,Error, Update
	 */
	public MessageTyp messageType;
	private char name;
	private PlaydAction PlayedAction;
	private int playersTurn;
	private int points;
	public PlaydAction m_PlaydAction;
	public GameState m_GameState;



	public void finalize() throws Throwable {

	}
	public MessageTyp Message(){
		return null;
	}

	public Message receive(Socket)(){
		return null;
	}

	public void send(Socket)(){

	}
}//end Message