package validatedScanner;

import java.util.HashMap;

/***
 * A container that holds all valid Strings
 * Can be passed to nextValidLine as an argument instead of String choices
 * Increases efficiency when making many calls with the same valid choices
 * 
 * This class can also be used directly to validate individual Strings
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
	
	/**
	 * Determines whether or not the set of valid Strings
	 * contains the given String
	 * @param s The String to validate
	 * @return True if String is in valid set
	 */
	public boolean contains(String s) {
		return map.containsKey(s);
	}

}
