package predictive;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.FileNotFoundException;
import java.util.Set;

/** 			
 * This class contains the test cases for all the 4 parts of WS2-3.
 * There are 5 tests, first 3 are for PredictivePrototype class: wordToSignature,
 * isValidWord and signatureToWords methods. 
 * Test4 tests WordSig class (getWord(), getSignature() and compareTo methods). 
 * And test5 tests signatureToWords method in ListDictionary class.		
 * 			
 * @author <Jing Meng>			
 * @version 2020-02-10			
 */		
public class Test_part1 {
	
	// ----------------------------test1 tests wordToSignature in PredictivePrototype----------------------------	
	@Test		
	public void test1() {		
		// test empty input	
		String expected1 = new String();	
		String actual1 = PredictivePrototype.wordToSignature("");	
		assertEquals(expected1,actual1, "failure in test1.1: actual value does not equal the expected one");	
		// test a lowercase word with no non-letter characters
		String expected2 = "42779";	
		String actual2 = PredictivePrototype.wordToSignature("happy");	
		assertEquals(expected2,actual2, "failure in test1.2: actual value does not equal the expected one");	
		// test a word with some capital letters	
		String expected3 = "42779";	
		String actual3 = PredictivePrototype.wordToSignature("HapPY");	
		assertEquals(expected3,actual3, "failure in test1.3: actual value does not equal the expected one");	 
		// test a word with some non-letter letter	
		String expected4 = "";	
		String actual4 = PredictivePrototype.wordToSignature("Hap P22345Y");	
		assertEquals(expected4,actual4, "failure in test1.4: actual value does not equal the expected one");		
	}	
	
	// ----------------------------test2 tests isValidWord in PredictivePrototype----------------------------	
	@Test		
	public void test2() {		
		// test empty input	
		boolean actual1 = PredictivePrototype.isValidWord("");	
		assertFalse(actual1, "failure in test2.1: actual value does not equal the expected one");	
		// test a lowercase word with no non-letter characters
		boolean actual2 = PredictivePrototype.isValidWord("happy");	
		assertTrue(actual2, "failure in test2.2: actual value does not equal the expected one");	
		// test a word with some capital letters	
		boolean actual3 = PredictivePrototype.isValidWord("HapPY");	
		assertTrue(actual3, "failure in test2.3: actual value does not equal the expected one");	 
		// test a word with some non-letter letter	
		boolean actual4 = PredictivePrototype.isValidWord("Hap P22345Y");	
		assertFalse(actual4, "failure in test2.4: actual value does not equal the expected one");		
	}
			
	// ----------------------------test3 tests signatureToWords in PredictivePrototype----------------------------
	// if the output set contains some common words 	
	@Test		
	public void test3() throws FileNotFoundException {		
		// test a number String with no non-number characters	
		String expected1 = new String("happy");	
		Set<String> actual1 = PredictivePrototype.signatureToWords("42779");	
		assertTrue(actual1.contains(expected1), "failure in test3.1: actual value does not equal the expected one");	
		// test a number String with some space	
		String expected2 = "happy";	
		Set<String>  actual2 = PredictivePrototype.signatureToWords("42 7   7 9 ");	
		assertTrue(actual2.contains(expected2), "failure in test3.2: actual value does not equal the expected one");	
		// test a number String with some non-number characters	
		String expected3 = "happy";	
		Set<String>  actual3 = PredictivePrototype.signatureToWords("HapPY42 7   7 9");	
		assertTrue(actual3.contains(expected3), "failure in test3.3: actual value does not equal the expected one");	
	}	
	
	// ----------------------------test4 tests WordSig class------------------------------------------------------		
	@Test		
	public void test4() throws FileNotFoundException {	
		WordSig ws1 = new WordSig("sunny","78669");
		WordSig ws2 = new WordSig("red", "733");
		// test getWord()
		String expected1 = "sunny";
		String actual1 = ws1.getWord();
		assertTrue(expected1==actual1, "failure in test4.1: actual value does not equal the expected one");
		// test getSignature()
		String expected2 = "78669";
		String actual2 = ws1.getSignature();
		assertTrue(expected2==actual2, "failure in test4.2: actual value does not equal the expected one");
		// test compareTo()
		int actual3 = ws2.compareTo(ws1); // r<s
		assertTrue(actual3 < 0, "failure in test4.3: actual value does not equal the expected one");
	}		
	
	// ----------------------------test5 tests signatureToWords in ListDictionary----------------------------
	// if the output set contains some common words 	
	@Test		
	public void test5() throws FileNotFoundException {	
		ListDictionary ld=new ListDictionary();
		// test a number String with no non-number characters	
		String expected1 = "happy";	
		Set<String> actual1 = ld.signatureToWords("42779");	
		assertTrue(actual1.contains(expected1), "failure in test5.1: actual value does not equal the expected one");	
		// test a number String with some space	
		String expected2 = "happy";	
		Set<String>  actual2 = ld.signatureToWords("42 7   7 9 ");	
		assertTrue(actual2.contains(expected2), "failure in test5.2: actual value does not equal the expected one");	
		// test a number String with some non-number characters	
		String expected3 = "happy";	
		Set<String>  actual3 = ld.signatureToWords("HapPY42 7   7 9");	
		assertTrue(actual3.contains(expected3), "failure in test5.3: actual value does not equal the expected one");	
	}
}