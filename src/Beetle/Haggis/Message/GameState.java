package Beetle.Haggis.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Beetle.Haggis.Server.Card;
import Beetle.Haggis.Server.Player;

/**
 * @author Lo�c Lavanchy
 * @version 1.1
 * @created 09-Nov-2014
 */
public class GameState {
	
	/**
	 * sortcards run of pairs.... ???
	 */
	public enum Combination {
		NEWTURN, SINGLE, PAIR, RUN
	}

	Combination actualCombination = Combination.NEWTURN;
	private ArrayList<Card> lastPlayedCards;

	/**
	 * player [0,1,2], cards[Card]
	 */
	private Player player[];
	private int playerTurns = 0;
	private boolean[] playerPlayed;
	/**
	 * after distributing cards. player first finish get this point, next gets 0
	 * point. "Haggis"
	 */
	private int gamePot = 0;

	/**
	 * Chek if it is allowed to played the selected cards. At this state of the
	 * game.
	 * 
	 * @param cards
	 *            List of selected cards
	 * @return True if the combination is correct and higher
	 */
	public boolean chekKombination(ArrayList<Card> cards) {
	
		Collections.sort(cards);
		boolean ansver = false;
		actualCombination = lastPlayedCards == null ? Combination.NEWTURN
				: actualCombination; // Avoid a crash in the case starting with
										// single
		switch (actualCombination) {
		case NEWTURN:
			ansver = run(cards) || pair(cards) || cards.size() == 1;
			break;

		case SINGLE:
			if (cards.get(0).getNumber() > lastPlayedCards.get(0).getNumber()) {
				ansver = true;
			}
			break;
		case PAIR:
			if (cards.size() == lastPlayedCards.size()
					&& cards.get(0).getNumber() > lastPlayedCards.get(0)
							.getNumber()) {
				ansver = pair(cards);
			}
			break;
		case RUN:
			if (cards.size() == lastPlayedCards.size()
					&& cards.get(0).getNumber() > lastPlayedCards.get(0)
							.getNumber()) {
				ansver = run(cards);
			}
			break;

		}
		return ansver;
	}

	/**
	 * Cheek if the given cards are a thirteen number of a kind (pair)
	 * 
	 * @param cards
	 *            Cards the player request to play, Sorted from smallest to
	 *            bigest
	 * @return Does al cards have the same value or added jokers
	 */
	private boolean pair(ArrayList<Card> cards) {
		boolean ansver = true;
		int pairValue = cards.get(0).getNumber();
		for (Card card : cards) {
			if (card.getNumber() != pairValue && card.getNumber() < 11) {
				ansver = false;
			}
		}
		return ansver;
	}

	/**
	 * Cheek if the cards are a correct run
	 * 
	 * @param cards
	 *            ards Cards the player request to play, Sorted from smallest to
	 *            bigest
	 * @return Return true if the run is correct including jokers.
	 */
	private boolean run(ArrayList<Card> cards) {
		boolean ansver = true;
		Card.Colour actualcolor = cards.get(0).getColour();
		int lastNumber = cards.get(0).getNumber();
		int usedJoker = 0;

		for (int i = 1; i < cards.size() - 1 - usedJoker; i++) {
			// Correct Colour
			if (cards.get(i).getColour().equals(actualcolor)) {
				// Correct Value
				if (cards.get(i).getNumber() + 1 == lastNumber) {
					lastNumber++;
					// Try to use a joker
				} else if (cards.get(cards.size() - 1 - usedJoker).getColour()
						.equals(Card.Colour.JOKER)) {
					usedJoker++;
					i--;
				} else {
					ansver = false;
					break;
				}
			} else if (!cards.get(i).getColour().equals(Card.Colour.JOKER)) {
				ansver = false;
				break;
			}
		}
		return ansver;
	}

	public Combination getActualCombination() {
		return actualCombination;
	}

	// Geter & Setter

	public void setActualCombination(Combination actualCombination) {
		this.actualCombination = actualCombination;
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
				player[i].addPoint(gamePot);
				gamePot = 0;
			} else {
				playerPlayed[i] = true;
			}
		}

	}

	public GameState(Player[] player) {
		super();
		this.player = player;
		for (int i = 0; i < player.length; i++) {
			playerPlayed[i] = true;
		}

	}

	public ArrayList<Card> getLastPlayedCards() {
		return lastPlayedCards;
	}

	public void setLastPlayedCards(ArrayList<Card> lastPlayedCards) {
		this.lastPlayedCards = lastPlayedCards;
	}

	public int getPlayerTurns() {
		return playerTurns;
	}

	/**
	 * Chek the value isn�t higher as the amount of player. If it is, the first
	 * player is set as actual player.
	 * 
	 * @param playerTurns
	 *            Next player 1, 2 [3]
	 */
	public int setPlayerTurns(int playerTurns) {
		this.playerTurns = playerTurns >= player.length ? 0 : playerTurns;
		return playerTurns;
	}

	public void setPlayer(Player p, int id) {
		player[id] = p;
	}

	public void setPlayerS(Player[] p) {
		player = p;
	}

	public Player[] getPlayers() {
		return player;
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
	 * @param gamePot
	 */
	public void setGamePot(int gamePot) {
		this.gamePot = gamePot;
	}

	public void setPlayerPlayed(boolean b, int playerTurns2) {
		playerPlayed[playerTurns2] = b;

	}

}// end GameState