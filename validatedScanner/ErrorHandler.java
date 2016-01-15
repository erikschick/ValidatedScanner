package validatedScanner;


/**
 * Use this class similarly to an ActionListener
 * 
 * ErrorHandler e = new ErrorHandler() {
 * 		@Override
 * 		public void handle() {
 * 			INSERT CODE HERE
 * 		}
 * };
 * 
 * 
 * @author Erik Schick
 *
 */
public abstract class ErrorHandler {
	
	/**
	 * Override this method to allow custom error behaviour
	 */
	public abstract void handle();

}
