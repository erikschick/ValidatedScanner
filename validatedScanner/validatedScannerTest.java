package validatedScanner;

import java.util.Scanner;

/**
 * A very basic series of tests for ValidatedScanner
 * Trivial behaviour will not/barely be tested
 * 
 * @author Erik Schick
 *
 */
class validatedScannerTest {
	
	public static void main(String[] args) {
		Scanner sn = new Scanner("Fail\nPass\n");
		ValidatedScanner vsn = new ValidatedScanner(sn);
		assert("Pass".equals(vsn.nextValidLine("Pass")));
		
		sn = new Scanner("7\n8\n100\n1000\n100001\n1001");
		assert(1001 == vsn.nextInt(1001, 2000));
		
		sn = new Scanner("1.34\n0.62\n-9.6532");
		assert(-9.6532 == vsn.nextDouble(-10.0, 0.0));
		
		sn = new Scanner("1.34\n0.62\n-9.6532");
		assert(-9.6532f == vsn.nextFloat(-10.0f, 0.0f));
		
		System.out.println("Passed all tests");
	}

}
