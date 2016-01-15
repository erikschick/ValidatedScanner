package validatedScanner;

import java.util.Scanner;

/**
 * A very basic series of tests for ValidatedScanner
 * Trivial behaviour will not/barely be tested
 * 
 * @author Erik Schick
 *
 */
class ValidatedScannerTest {
	static int errorCount = 0;
	
	private static void myAssert(boolean condition) throws Exception {
		if(condition == false) {
			throw new Exception();
		}
	}
	
	public static void main(String[] args) throws Exception {
		ValidatedScanner vsn = new ValidatedScanner(new Scanner("Fail\nPass\n"));
		vsn.suppressErrors();
		myAssert("Pass".equals(vsn.nextValidLine("Pass")));
		
		vsn.setScanner(new Scanner("7\n8\n100\n1000\n100001\n1002"));
		myAssert(1002 == vsn.nextInt(1001, 2000));
		
		vsn.setScanner(new Scanner("1.34\n0.62\n-9.6532"));
		myAssert(-9.6532 == vsn.nextDouble(-10.0, 0.0));
		
		vsn.setScanner(new Scanner("1.34\n0.62\n-9.6532"));
		myAssert(-9.6532f == vsn.nextFloat(-10.0f, 0.0f));
		
		
		// Testing ErrorHandler
		vsn.setErrorHandler(new ErrorHandler() {
			@Override
			public void handle() {
				errorCount++;
			}
		});
		vsn.setScanner(new Scanner("7\n8\n100\n1000\n100001\n1001"));
		myAssert(1001 == vsn.nextInt(1001, 2000));
		myAssert(errorCount == 5);
		vsn.suppressErrors();
		vsn.setScanner(new Scanner("7\n8\n100\n1000\n100001\n1001"));
		myAssert(1001 == vsn.nextInt(1001, 2000));
		myAssert(errorCount == 5);
		
		
		System.out.println("Passed all tests");
	}

}
