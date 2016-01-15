package validatedScanner;

import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * A very basic series of tests for ValidatedScanner
 * Trivial behaviour will not/barely be tested
 * 
 * @author Erik Schick
 *
 */
class ValidatedScannerTest {
	
	private static void myAssert(boolean condition) throws Exception {
		if(condition == false) {
			throw new Exception();
		}
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sn = new Scanner("Fail\nPass\n");
		ValidatedScanner vsn = new ValidatedScanner(sn);
		vsn.suppressErrors();
		
		myAssert("Pass".equals(vsn.nextValidLine("Pass")));
		
		sn = new Scanner("7\n8\n100\n1000\n100001\n1002");
		vsn.setScanner(sn);
		myAssert(1002 == vsn.nextInt(1001, 2000));
		
		sn = new Scanner("1.34\n0.62\n-9.6532");
		vsn.setScanner(sn);
		myAssert(-9.6532 == vsn.nextDouble(-10.0, 0.0));
		
		sn = new Scanner("1.34\n0.62\n-9.6532");
		vsn.setScanner(sn);
		myAssert(-9.6532f == vsn.nextFloat(-10.0f, 0.0f));
		
		
		
		System.out.println("Passed all tests");
	}

}
