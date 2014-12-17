/**
 * 
 */
package Beetle.Haggis.Server;

import java.util.Comparator;

/**
 ** @author Marco Mancuso
 *
 */
public class SelectNumberFirst implements Comparator<Card>{

	@Override
	public int compare(Card arg0, Card arg1) {
		int answer = arg0.getNumber() - arg1.getNumber();		
		if (answer == 0){
			answer = arg0.getColour().compareTo(arg0.getColour());
		}
		return answer;
	}
}
