package validatedScanner;

/***
 * A user-implementable object that can be passed to a ValidatedScanner
 * to extend the functionality of the validation.
 * 
 * @author Erik Schick
 *
 */
public abstract class NumberValidator {
	/**
	 * Override this method to allow custom validation behaviour.
	 * Double is used so that int, float, and double are supported
	 */
	public abstract boolean valid(double input);
}
