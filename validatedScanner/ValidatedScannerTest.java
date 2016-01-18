package validatedScanner;

import java.util.Scanner;

/**
 * A very basic series of tests for ValidatedScanner
 * Testing is definitely not thorough.
 * Trivial behaviour will not/barely be tested
 * 
 * @author Erik Schick
 *
 */
class ValidatedScannerTest {
	final static int TEST_LOOPS = 100;
	static int errorCount = 0;
	
	private static void myAssert(boolean condition) throws Exception {
		if(condition == false) {
			throw new Exception();
		}
	}
	
	@SuppressWarnings("deprecation")
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
		
		
		
		
		//------------------------------------------------------------------------------
		//------------------------------------------------------------------------------
		// Benchmarking nextValidLine naive vs hashmap vs hashmap with premade map
		
		String choices[] = {"These", "Are", "Valid", "Choices", "To", "Use", "For",
				"This", "String", "Entry", "The", "List", "Should", "Be", "Longer",
				"But", "This", "Will", "Do", "Presently" };
		
		Long start = System.nanoTime();
		for(int i = 0; i < TEST_LOOPS; i++) {
			vsn.setScanner(new Scanner("Lines\nwriting\nwords\nthese\nthat\nbe"
					+ "\nquestioning\nif\nthis\nhas\nany\nresult\nbut\nit\nshould"
					+ "\nso\nI'll\nkeep\nadding\nstrings\ncontinually\nlonger\nthis"
					+ "\nis\nso\nlong\nnow\nbut\nit\nshould\naccomplish\nsomething"));
			try {
				vsn.nextValidLineNaive(choices);
			} catch (Exception e) {
				vsn.setScanner(new Scanner("Lines\nwriting\nwords\nthese\nthat\nbe"
						+ "\nquestioning\nif\nthis\nhas\nany\nresult\nbut\nit\nshould"
						+ "\nso\nI'll\nkeep\nadding\nstrings\ncontinually\nlonger\nthis"
						+ "\nis\nso\nlong\nnow\nbut\nit\nshould\naccomplish\nsomething"));
			}
		}
		
		double time = (System.nanoTime()-start) / (double)1000000000;
		System.out.println(TEST_LOOPS + "x naive took: " + time + " seconds");
		
		
		start = System.nanoTime();
		for(int i = 0; i < TEST_LOOPS; i++) {
			vsn.setScanner(new Scanner("Lines\nwriting\nwords\nthese\nthat\nbe"
					+ "\nquestioning\nif\nthis\nhas\nany\nresult\nbut\nit\nshould"
					+ "\nso\nI'll\nkeep\nadding\nstrings\ncontinually\nlonger\nthis"
					+ "\nis\nso\nlong\nnow\nbut\nit\nshould\naccomplish\nsomething"));
			try {
				vsn.nextValidLine(choices);
			} catch (Exception e) {
				vsn.setScanner(new Scanner("Lines\nwriting\nwords\nthese\nthat\nbe"
						+ "\nquestioning\nif\nthis\nhas\nany\nresult\nbut\nit\nshould"
						+ "\nso\nI'll\nkeep\nadding\nstrings\ncontinually\nlonger\nthis"
						+ "\nis\nso\nlong\nnow\nbut\nit\nshould\naccomplish\nsomething"));
			}
		}
		
		time = (System.nanoTime()-start) / (double)1000000000;
		System.out.println(TEST_LOOPS + "x hashmap took: " + time + " seconds");
		
		ValidStrings vs = new ValidStrings(choices);
		start = System.nanoTime();
		for(int i = 0; i < TEST_LOOPS; i++) {
			vsn.setScanner(new Scanner("Lines\nwriting\nwords\nthese\nthat\nbe"
					+ "\nquestioning\nif\nthis\nhas\nany\nresult\nbut\nit\nshould"
					+ "\nso\nI'll\nkeep\nadding\nstrings\ncontinually\nlonger\nthis"
					+ "\nis\nso\nlong\nnow\nbut\nit\nshould\naccomplish\nsomething"));
			try {
				vsn.nextValidLine(vs);
			} catch (Exception e) {
				vsn.setScanner(new Scanner("Lines\nwriting\nwords\nthese\nthat\nbe"
						+ "\nquestioning\nif\nthis\nhas\nany\nresult\nbut\nit\nshould"
						+ "\nso\nI'll\nkeep\nadding\nstrings\ncontinually\nlonger\nthis"
						+ "\nis\nso\nlong\nnow\nbut\nit\nshould\naccomplish\nsomething"));
			}
		}
		
		time = (System.nanoTime()-start) / (double)1000000000;
		System.out.println(TEST_LOOPS + "x hashmap with premade map took: " + time + " seconds");
		
		//------------------------------------------------------------------------------
		//------------------------------------------------------------------------------
		
		
		
		// Testing custom Validators
		
		NumberValidator evenWholeNumber = new NumberValidator() {
			@Override
			public boolean valid(double input) {
				return (input % 2 == 0);
			}
		};
		vsn.setScanner(new Scanner("1\n3\n5\n4\n"));
		myAssert(vsn.nextInt(evenWholeNumber) == 4);
		
		vsn.setScanner(new Scanner("1.0\n3.2\n5.0\n4.1\n4.0"));
		myAssert(vsn.nextFloat(evenWholeNumber) == 4.0);
		
		vsn.setScanner(new Scanner("1.0\n3.2\n5.0\n4.1\n4.0"));
		myAssert(vsn.nextDouble(evenWholeNumber) == 4.0);
		
		
		StringValidator containsVowel = new StringValidator() {
			@Override
			public boolean valid(String input) {
				for(char c : input.toLowerCase().toCharArray()) {
					if(c == 'a' | c == 'e' | c == 'i'
							| c == 'o' | c == 'u') {
						return true;
					}
				}
				return false;
			}
		};
		
		vsn.setScanner(new Scanner("svn\ncdn\nPDF\nCAD"));
		myAssert(vsn.nextValidLine(containsVowel).equals("CAD"));
		
		
		System.out.println("\nPassed all tests");
	}

}
