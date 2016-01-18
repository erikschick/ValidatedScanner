package validatedScanner;

import java.util.HashMap;

/***
 * A container that holds all valid Strings
 * Can be passed to nextValidLine as an argument instead of String choices
 * Increases efficiency when making many calls with the same valid choices
 * 
 * @author Erik Schick
 *
 */
public class ValidStrings {
	HashMap<String, Boolean> map = new HashMap<String, Boolean>();
	
	public ValidStrings(String... choices) {
		for(String s : choices) {
			map.put(s, true);
		}
	}
	
	public boolean contains(String s) {
		return map.containsKey(s);
	}

}
