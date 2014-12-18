package Beetle.Haggis.Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import Beetle.Haggis.Server.Card;
import Beetle.Haggis.Server.Player;

/**
 * @author Loïc Lavanchy
 * @version 1.1
 * @created 09-Nov-2014
 */
public class GameState implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1l;

	/**
	 * sortcards run of pairs.... ???
	 */
	public enum Combination {
		NEWTURN, SINGLE, PAIR, RUN
	}

	static Combination curentCombination = Combination.NEWTURN;
	private Stack<Card> lastPlayedCards;

	private Player players[]; // player [0,1,2], cards[Card]
	private int playerTurns = 0;
	private boolean[] playerPlayed;
	private int version = 0;

	/**
	 * after distributing cards. player first finish get this point, next gets 0
	 * point. "Haggis"
	 */
	private int gamePot = 0;

	/**
	 * Check if it is allowed to played the selected cards. At this state of the
	 * game.
	 * 
	 * @param cards
	 *            List of selected cards
	 * @return True if the combination is correct and higher
	 */
	public boolean checkCombinations(ArrayList<Card> cards) {
		boolean answer = false;
		if (cards.size() == 0 || cards == null) {
			return false; // Catch if the player unselect al his cards.
		}
		Collections.sort(cards);

		curentCombination = lastPlayedCards == null ? Combination.NEWTURN
				: curentCombination; // Avoid a crash in the case starting with
										// single

		switch (curentCombination) {
		case NEWTURN:
			answer = cards.size() == 1 || pair(cards)
					|| streetCombination(cards);
			break;

		case SINGLE:
			if (cards.get(0).getNumber() > lastPlayedCards.get(0).getNumber()) {
				answer = true;
			}
			break;
		case PAIR:
			if (cards.size() == lastPlayedCards.size()
					&& cards.get(0).getNumber() > lastPlayedCards.get(0)
							.getNumber()) {
				answer = pair(cards);
			}
			break;
		case RUN:
			if (cards.size() == lastPlayedCards.size()
					&& cards.get(0).getNumber() > lastPlayedCards.get(0)
							.getNumber()) {
				answer = streetCombination(cards);
			}
			break;

		}

		if (answer  && curentCombination != Combination.NEWTURN
				&& cards.get(0).getNumber() < lastPlayedCards.get(0)
						.getNumber()) {
			answer = false;
			
		}
		if( lastPlayedCards!=null ){
			if(cards.get(0).getNumber()<= lastPlayedCards.get(0).getNumber()|| cards.size() != lastPlayedCards.size()){
				answer =false;
			}
			
		}
		
		
		return answer;
	}

	/**
	 * Cheek if the given cards are a thirteen number of a kind (pair)
	 * 
	 * @param cards
	 *            Cards the player request to play, Sorted from smallest to
	 *            bigest
	 * @return Does al cards have the same value or added jokers
	 */
	private static boolean pair(ArrayList<Card> cards) {
		boolean answer = true;
		int pairValue = cards.get(0).getNumber();
		for (Card card : cards) {
			if (card.getNumber() != pairValue && card.getNumber() < 11) {
				answer = false;
			}
		}
		return answer;
	}

	/**
	 * Cheek if the cards are a correct run
	 * 
	 * @param cards
	 *            ards Cards the player request to play, Sorted from smallest to
	 *            bigest
	 * @return Return true if the run is correct including jokers.
	 */
	private static boolean streetCombination(ArrayList<Card> cards) {
		boolean answer = true;
		Card.Colour actualcolor = cards.get(0).getColour();
		int lastNumber = cards.get(0).getNumber();
		int usedJoker = 0;
		if (cards.size() < 3) {
			return false;
		} // Minimum of 3 cards for a street
		for (int i = 1; i < cards.size() - usedJoker; i++) {
			// Correct Colour
			if (cards.get(i).getColour().equals(actualcolor)) {
				// Correct Value
				if (cards.get(i).getNumber() - 1 == lastNumber) {
					lastNumber++;
					// Try to use a joker
				} else if (cards.get(cards.size() - 1 - usedJoker).getColour()
						.equals(Card.Colour.JOKER)) {
					usedJoker++;
					lastNumber++;
					i--;
				} else {
					answer = false;
					break;
				}
			} else if (!cards.get(i).getColour().equals(Card.Colour.JOKER)) {
				answer = false;
				break;
			}
		}
		return answer;
	}

	/**
	 * Reset the array player played and gives the point to the player with
	 * played last.
	 * 
	 * @param playerTurns
	 */
	public void newRound() {
		for (int i = 0; i < playerPlayed.length; i++) {

			if (playerPlayed[i] == true) {
				playerTurns = i;
				
				gamePot = 0;
			} else {
				playerPlayed[i] = true;
			}
		}
		lastPlayedCards = null;

	}

	public GameState(Player[] players) {
		super();
		this.players = players;
		playerPlayed = new boolean[players.length];
		for (int i = 0; i < players.length; i++) {
			playerPlayed[i] = true;
		}

	}

	// Geter & Setter
	public Combination getActualCombination() {
		return curentCombination;
	}

	public void setActualCombination(Combination actualCombination) {
		GameState.curentCombination = actualCombination;
	}

	public Stack<Card> getLastPlayedCards() {
		return lastPlayedCards;
	}

	public void setLastPlayedCards(ArrayList<Card> playedCards) {
		Stack<Card> cards = new Stack<Card>();
		for (Card card : playedCards) {
			cards.push(card);
		}
		lastPlayedCards = cards;
	}


	public int getPlayerTurns() {
		return playerTurns;
	}

	/**
	 * Chek the value isn’t higher as the amount of player. If it is, the first
	 * player is set as actual player.
	 * 
	 * @param playerTurns
	 *            Next player 1, 2 [3]
	 */
	public int setPlayerTurns(int pTurns) {
		int inPlayr = players.length;
		this.playerTurns = pTurns % inPlayr;
		// playerTurns >= players.length ? 0 : playerTurns;
		return this.playerTurns;
	}

	/**
	 * Chek the value isn’t higher as the amount of player. If it is, the first
	 * player is set as actual player.
	 * 
	 * @param playerTurns
	 *            Next player 1, 2 [3]
	 */
	public int nextPlayer() {

		this.playerTurns = (1 + playerTurns) % players.length;
		// playerTurns >= players.length ? 0 : playerTurns;
		return playerTurns;
	}

	public void setPlayer(Player p, int id) {
		players[id] = p;
	}

	public void setPlayers(Player[] p) {
		players = p;
	}

	public Player[] getPlayers() {
		return players;
	}

	public boolean[] getPlayerPlayed() {
		return playerPlayed;
	}

	public void setPlayerPlayed(boolean[] playerPlayed) {
		this.playerPlayed = playerPlayed;
	}

	/**
	 * Haggis
	 * 
	 * @return
	 */
	public int getGamePot() {
		return gamePot;
	}

	/**
	 * Hggis
	 * 
	 * @param gamePot
	 */
	public void setGamePot(int gamePot) {
		this.gamePot = gamePot;
	}

	public void setPlayerPlayed(boolean b, int playerTurns2) {
		playerPlayed[playerTurns2] = b;

	}

	public void setPlayerName(int id, String name) {
		players[id].setName(name);
		players[id].setId(id);
	}

	public String getPlayerName(int id) {
		return players[id].getName();

	}

	public void versionCounter() {
		version++;

	}

	public int getVersion() {
		return version;
	}

	public boolean setNewCombination(ArrayList<Card> cards) {
		boolean answer = false;
		if (cards.size() == 1) {
			curentCombination = Combination.SINGLE;
			answer = true;
		} else if (streetCombination(cards)) {
			curentCombination = Combination.RUN;
			answer = true;
		} else if (pair(cards)) {
			curentCombination = Combination.PAIR;
			answer = true;
		}
		return answer;

	}

	public Combination getCurentCombination() {
		return curentCombination;
	}

}
