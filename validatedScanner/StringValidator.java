package validatedScanner;

/***
 * A user-implementable object that can be passed to a ValidatedScanner
 * to extend the functionality of the validation.
 * 
 * @author Erik Schick
 *
 */
public abstract class StringValidator {
	public final static StringValidator ALWAYS_VALID = new StringValidator() {
		@Override
		public boolean valid(String input) {
			return true;
		}
	};
	
	/**
	 * Override this method to allow custom validation behaviour
	 */
	public abstract boolean valid(String input);
}
