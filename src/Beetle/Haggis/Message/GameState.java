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
	 * sortcards
	 * run of pairs.... ???
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
	private boolean[] playerPlayed;
	/**
	 * after distributing cards. player first finish get this point, next gets 0
	 * point.
	 */
	private int restCardValue = 0;
	private int gamePot=0;



	/**
	 * Chek if it is allowed to played the selected cards. At this state of the game. 
	 * @param cards List of selected cards
	 * @return True if the combination is correct and higher 
	 */
	public boolean chekKombination(ArrayList<Card> cards) {
		// TODO LL Sort Cards
		boolean ansver = false;
		actualCombination= lastPlayedCards==null? Combination.NEWTURN: actualCombination; //Avoid a crash in the case starting with single 
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

	public void newRound(int playerTurns) {
		this.playerTurns = playerTurns;
				
		for (boolean pP : playerPlayed) {
			pP=true;
		}
		
	}
	public GameState( Player[] player) {
		super();
		this.player = player;
		for (int i=0; i< player.length;i++){
			playerPlayed[i]= true;
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

	public void setPlayerTurns(int playerTurns) {
		this.playerTurns = playerTurns;
	}

	/**
	 * 
	 * @param reset Set the value of rest cards to 0 if the user get the point of the Haggis
	 * @return
	 */
	public int getRestCardValue(boolean reset) {
		int restV = restCardValue;
		restCardValue = reset ? 0 : restCardValue;
		return restV;
	}

	public void setRestCardValue(int restCardValue) {
		this.restCardValue = restCardValue;
	}

	public void setPlayer(Player p, int id){
		player[id]= p;
	}
	public void setPlayerS(Player[] p){
		player= p;
	}
	
	public Player[] getPlayers(){
		return player;
	}

	public boolean[] getPlayerPlayed() {
		return playerPlayed;
	}

	public void setPlayerPlayed(boolean[] playerPlayed) {
		this.playerPlayed = playerPlayed;
	}

	public int getGamePot() {
		return gamePot;
	}

	public void setGamePot(int gamePot) {
		this.gamePot = gamePot;
	}
	
	
	

}// end GameState