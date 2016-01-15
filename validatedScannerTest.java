package validatedScanner;

import java.util.Scanner;

class validatedScannerTest {
	public static void main(String[] args) {
		Scanner sn = new Scanner("Fail\nPass\n");
		ValidatedScanner vsn = new ValidatedScanner(sn);
		assert("Pass".equals(vsn.nextValidLine("Pass")));
		System.out.println("Passed all tests");
	}

}
