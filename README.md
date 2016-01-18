# ValidatedScanner
A wrapper for Java Scanner to support automatic validation of input.

The user will be repeatedly asked for input until valid input is given.

##Repeated requests
If multiple input requests will be made with the same choices of valid Strings, make a new ValidStrings object with the choices and pass the ValidStrings object as an argument instead. This avoids remaking the internal validation table with each request.
#####Benchmark results for 10000 input loops on the same input:
Deprecated method: 0.315696913 seconds<br>
Regular: 0.172624646 seconds<br>
Regular with ValidStrings: 0.143905804 seconds<br>

##Input errors:
After each invalid input, an error occurs. Errors trigger the handle() method in an ErrorHandler that is stored in the ValidatedScanner.

####setErrorMessage()
New error messages can be set to a String. Newlines are not automatically appended.

####setErrorHandler()
An ErrorHandler can be passed to the ValidatedScanner in the same way that an ActionListener can be passed to a Swing component.

<br />Example:
```java
ValidatedScanner vs = new ValidatedScanner(someScanner);
vs.setErrorHandler(new ErrorHandler() {
    @Override
    public void handle() {
        errorCount++;
        System.out.println("Invalid input");
    }
});
```
