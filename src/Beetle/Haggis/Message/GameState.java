package Beetle.Haggis.Message;

import java.util.ArrayList;

import Beetle.Haggis.Server.Card;
import Beetle.Haggis.Server.Player;

/**
 * @author Loïc Lavanchy
 * @version 1.1
 * @created 09-Nov-2014
 */
public class GameState {
	// TODO LL Gamestate
	/**
	 * chek kombination getter ( Setter)
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
	private int playerTurns;
	/**
	 * after distributing cards. player first finish get this point, next gets 0
	 * point.
	 */
	private int restCardValue = 0;

	public GameState() {

	}

	/**
	 * Chek if it is allowed to played the selected cards. At this state of the game. 
	 * @param cards List of selected cards
	 * @return True if the combination is correct and higher 
	 */
	public boolean chekKombination(ArrayList<Card> cards) {
		// TODO Sort Cards
		boolean ansver = false;
		switch (actualCombination) {
		case NEWTURN:
			ansver = run(cards) ||pair(cards)||cards.size() ==1;
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

	public ArrayList<Card> getLastPlayedCards() {
		return lastPlayedCards;
	}

	public void setLastPlayedCards(ArrayList<Card> lastPlayedCards) {
		this.lastPlayedCards = lastPlayedCards;
	}

	public int getPlayerTurns() {
		return playerTurns;
	}

	public void setPlayerTurns(int playerTurns) {
		this.playerTurns = playerTurns;
	}

	public int getRestCardValue() {
		return restCardValue;
	}

	public void setRestCardValue(int restCardValue) {
		this.restCardValue = restCardValue;
	}



}// end GameState