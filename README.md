# ValidatedScanner
A wrapper for Java Scanner to support automatic validation of input.

The user will be repeatedly asked for input until valid input is given.

##Input errors:
After each invalid input, an error occurs. Errors trigger the handle() method in an ErrorHandler that is stored in the ValidatedScanner.

###setErrorMessage()
New error messages can be set to a String. Newlines are not automatically appended.

###setErrorHandler()
An ErrorHandler can be passed to the ValidatedScanner in the same way that an ActionListener can be passed to a Swing component.

Example:
vsn.setErrorHandler(new ErrorHandler() {
		@Override
		public void handle() {
			errorCount++;
			System.out.println("Invalid input");
	}
});
