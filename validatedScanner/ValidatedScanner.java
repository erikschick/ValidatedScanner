// TODO add support for custom Validator objects to be passed in to all methods
package validatedScanner;

import java.util.Scanner;

/**
 * This package wraps a Scanner to ensure valid input
 * When using this package it is crucial to ensure that the scanner
 * is not closed remotely.
 * 
 * The scanner class is final so there are methods to pass calls to Scanner
 * 
 * @author Erik Schick
 *
 */
public class ValidatedScanner {
	// The scanner to be used for input
	private Scanner sn;
	
	// The current ErrorHandler set
	private ErrorHandler errorHandler;
	
	
	/**
	 * Initialize a ValidatedScanner to wrap a Scanner
	 * @param sn the Scanner to wrap
	 */
	public ValidatedScanner(Scanner sn) {
		this.sn = sn;
		errorHandler = ErrorHandler.DEFAULT_ERROR;
	}
	
	
	/**
	 * Set this ValidatedScanner to wrap a new Scanner
	 * @param sn the Scanner to wrap
	 */
	public void setScanner(Scanner sn) {
		this.sn = sn;
	}
	
	
	/**
	 * Closes this Scanner
	 */
	public void close() {
		sn.close();
	}
	
	
	private void onError() {
		errorHandler.handle();
	}
	
	
	/**
	 * Allows the passing in of an ErrorHandler with custom code
	 * @param e an ErrorHandler
	 */
	public void setErrorHandler(ErrorHandler e) {
		errorHandler = e;
	}
	
	
	/**
	 * Sets a new error message
	 * @param s the message to print on input errors
	 */
	public void setErrorMessage(String message) {
		errorHandler = new ErrorHandler() {
			@Override
			public void handle() {
				System.out.print(message);
			}
		};
	}
	
	
	/**
	 * Disables the printing of error messages
	 */
	public void suppressErrors() {
		setErrorMessage("");
	}
	
	
	
	/**
	 * Gets the next String that is in the set of valid choices
	 * @param choices The set of valid Strings
	 * @return The valid String input
	 */
	public String nextValidLine(String... choices) {
		ValidStrings vs = new ValidStrings(choices);
		String input;
		input = sn.nextLine();
		while(!vs.contains(input)) {
			onError();
			input = sn.nextLine();
		}
		
		return input;
	}
	
	
	/**
	 * Gets the next String that is in the HashMap of choices
	 * @param choices The HashMap of valid Strings, acquired with getStringMap
	 * @return The valid String input
	 */
	public String nextValidLine(ValidStrings choices) {
		String input;
		input = sn.nextLine();
		while(!choices.contains(input)) {
			onError();
			input = sn.nextLine();
		}
		
		return input;
	}
	
	
	@Deprecated
	public String nextValidLineNaive(String... choices) {
		String input;
		while(true) {
			input = sn.nextLine();
			for(String choice : choices) {
				if(input.equals(choice)) {
					return input;
				}
			}
			onError();
		}
	}
	
	
	/**
	 * Gets the next int that is in range
	 * @param min The minimum valid value
	 * @param max The maximum valid value
	 * @return The valid int input
	 */
	public int nextInt(int min, int max) {
		int input = sn.nextInt();
		while(input < min || input > max) {
			onError();
			input = sn.nextInt();
		}
		return input;
	}
	
	
	/**
	 * Gets the next double that is in range
	 * @param min The minimum valid value
	 * @param max The maximum valid value
	 * @return The valid double input
	 */
	public double nextDouble(double min, double max) {
		double input = sn.nextDouble();
		while(input < min || input > max) {
			onError();
			input = sn.nextDouble();
		}
		return input;
	}
	
	
	/**
	 * Gets the next float that is in range
	 * @param min The minimum valid value
	 * @param max The maximum valid value
	 * @return The valid float input
	 */
	public float nextFloat(float min, float max) {
		float input = sn.nextFloat();
		while(input < min || input > max) {
			onError();
			input = sn.nextFloat();
		}
		return input;
	}
	
	
	/** 
	 *
	 * Scanner call passing methods
	 * See Scanner documentation for any info
	 * 
	 */
	
	public boolean hasNext() { return sn.hasNext(); }
	public String next() { return sn.next(); }
	
	public boolean hasNextLine() { return sn.hasNextLine(); }
	public String nextLine() { return sn.nextLine(); }
	
	public boolean hasNextInt() { return sn.hasNextInt(); }
	public int nextInt() { return sn.nextInt(); }
	
	public boolean hasNextDouble() { return sn.hasNextDouble(); }
	public double nextDouble() { return sn.nextDouble(); }
	
	public boolean hasNextFloat() { return sn.hasNextFloat(); }
	public float nextFloat() { return sn.nextFloat(); }
}
