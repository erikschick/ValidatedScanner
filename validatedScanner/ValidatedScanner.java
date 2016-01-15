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
	
	// The error message to print
	private String errorMessage = "Input error\n";
	
	
	/**
	 * Initialize a ValidatedScanner to wrap a Scanner
	 * @param sn the Scanner to wrap
	 */
	public ValidatedScanner(Scanner sn) {
		this.sn = sn;
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
	
	
	/**
	 * Sets a new error message
	 * @param s the message to print on input errors
	 */
	public void setError(String s) {
		errorMessage = s;
	}
	
	
	/**
	 * Disables the printing of error messages
	 */
	public void suppressErrors() {
		setError("");
	}
	
	
	public String nextValidLine(String... choices) {
		/* TODO use a bucket method for quicker validation
		 * with large amount of choices. Allow user to manually
		 * preload and set a "set of buckets" for multiple calls
		 * with the same restrictions
		 */
				
		String input = "";
		while(true) {
			input = sn.nextLine();
			for(String choice : choices) {
				if(input.equals(choice)) {
					return input;
				}
			}
			System.out.print(errorMessage);
		}
	}
	
	public int nextInt(int min, int max) {
		int input = sn.nextInt();
		while(input < min || input > max) {
			System.out.print(errorMessage);
			input = sn.nextInt();
		}
		return input;
	}
	
	public double nextDouble(double min, double max) {
		double input = sn.nextDouble();
		while(input < min || input > max) {
			System.out.print(errorMessage);
			input = sn.nextDouble();
		}
		return input;
	}
	
	public float nextFloat(float min, float max) {
		float input = sn.nextFloat();
		while(input < min || input > max) {
			System.out.print(errorMessage);
			input = sn.nextFloat();
		}
		return input;
	}
	
	
	/** 
	 *
	 * Scanner call passing methods
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
