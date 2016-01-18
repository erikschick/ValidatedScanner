package validatedScanner;


/**
 * ErrorHandler handle() method is called whenever an input
 * error happens with ValidatedScanner
 * 
 * Use this class similarly to an ActionListener
 * 
 * 
 * @author Erik Schick
 *
 */
public abstract class ErrorHandler {
	public final static ErrorHandler DEFAULT_ERROR = new ErrorHandler() {
		@Override
		public void handle() {
			System.out.print("Input error\n");
		}
	};
	
	/**
	 * Override this method to allow custom error behaviour
	 */
	public abstract void handle();

}
