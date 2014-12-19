package Beetle.Haggis.Network;

import java.util.Comparator;

/**
 * @author Marco Mancuso
 *
 */
public class SortColourFirst implements Comparator<Card>{

	@Override
	public int compare(Card arg0, Card arg1) {
		int answer = arg0.getColour().compareTo(arg0.getColour());
		if (answer == 0){
			answer = arg0.getNumber() - arg1.getNumber();
		}
		return answer;
	}
}
