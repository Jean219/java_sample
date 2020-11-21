package predictive;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/** 			
 * This class contains the test cases for all the 4 parts of WS2-3.
 * There are 5 tests, first 3 are for PredictivePrototype class: wordToSignature,
 * isValidWord and signatureToWords methods. 
 * Test4 tests WordSig class (getWord(), getSignature() and compareTo methods). 
 * And test5 tests signatureToWords method in ListDictionary class.		
 * 			
 * @author <Jing Meng>			
 * @version 2020-02-18			
 */		
public class Test_Dictionary {
	// part1 PredictivePrototype
	// ----------------------------test1 tests isValidWord in PredictivePrototype----------------------------	
	@Test		
	public void test1() {		
		// test empty input	
		boolean actual1 = PredictivePrototype.isValidWord("");	
		assertFalse(actual1, "failure in test1.1: actual value does not equal the expected one");	
		// test a lower-cased word with no non-letter characters
		boolean actual2 = PredictivePrototype.isValidWord("happy");	
		assertTrue(actual2, "failure in test1.2: actual value does not equal the expected one");	
		// test a word with some capital letters	
		boolean actual3 = PredictivePrototype.isValidWord("HapPY");	
		assertTrue(actual3, "failure in test1.3: actual value does not equal the expected one");	 
		// test a word with some non-letter letter	
		boolean actual4 = PredictivePrototype.isValidWord("Hap P22345Y");	
		assertFalse(actual4, "failure in test1.4: actual value does not equal the expected one");		
	}
	
	
	// ----------------------------test2 tests wordToSignature in PredictivePrototype----------------------------	
	@Test		
	public void test2() {		
		// test empty input	
		String expected1 = new String();	
		String actual1 = PredictivePrototype.wordToSignature("");	
		assertEquals(expected1,actual1, "failure in test2.1: actual value does not equal the expected one");	
		// test a lowercase word with no non-letter characters
		String expected2 = "42779";	
		String actual2 = PredictivePrototype.wordToSignature("happy");	
		assertEquals(expected2,actual2, "failure in test2.2: actual value does not equal the expected one");	
		// test a word with some capital letters	
		String expected3 = "42779";	
		String actual3 = PredictivePrototype.wordToSignature("HapPY");	
		assertEquals(expected3,actual3, "failure in test2.3: actual value does not equal the expected one");	 
		// test a word with some non-letter letter, as the input will be defined as not valid, it returns nothing	
		String actual4 = PredictivePrototype.wordToSignature("Hap P22345Y");	
		assertTrue(actual4.isEmpty(), "failure in test2.4");		
	}	
	
			
	// ----------------------------test3 tests signatureToWords in PredictivePrototype----------------------------
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
		// test input a word(no numeric characters), it should return nothing
		Set<String>  actual4 = PredictivePrototype.signatureToWords("happy");	
		assertTrue(actual4.isEmpty(), "failure in test3.4: actual value does not equal the expected one");	
	}	
	
	
	// part2 ListDictionary
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
		// test input a word(no numeric characters), it should return nothing
		Set<String>  actual4 = ld.signatureToWords("happy");	
		assertTrue(actual4.isEmpty(), "failure in test5.4");	
	}
	
	
	// ----------------------------test6 tests makeDictionary method in ListDictionary----------------------------
	@Test		
	public void test6() throws FileNotFoundException {	
		Scanner in= new Scanner(new File(System.getProperty("user.dir")+"\\src\\words-test")); 		
		String dicWord;				
		ArrayList<WordSig> dictionary=new ArrayList<WordSig>();		
		while(in.hasNextLine()){
			dicWord=in.nextLine();	
			if(PredictivePrototype.isValidWord(dicWord)) {
				dictionary.add(new WordSig(dicWord.toLowerCase(), PredictivePrototype.wordToSignature(dicWord)));      
			}   
        }		
		in.close();  		
		Comparator<WordSig> mySort = (e1, e2) -> e1.getSignature().compareTo(e2.getSignature());
		Collections.sort(dictionary, mySort);
//		for(int i=0;i<dictionary.size();i++) {
//			System.out.println("word:"+dictionary.get(i).getWord()+" signature:"+dictionary.get(i).getSignature());
//		}
		// test if the first word is "a" (if the dictionary is sorted)
		String expected1 = "a";	
		String actual1 = dictionary.get(0).getWord();	
		assertEquals(expected1,actual1, "failure in test6.1");	
		// test if the not valid word stored in the dictionary
		WordSig test1 = new WordSig("nee5d","6333");
		WordSig test2 = new WordSig("need","6333");
		assertFalse(dictionary.contains(test1), "failure in test6.2.1");	
		assertFalse(dictionary.contains(test2), "failure in test6.2.2");			
	}
	
	
	// part3 MapDictionary
	// ----------------------------test7 tests signatureToWords in MapDictionary----------------------------
	@Test		
	public void test7() throws FileNotFoundException {
		MapDictionary md=new MapDictionary(System.getProperty("user.dir")+"\\src\\words");
		// test a number String with no non-number characters	
		String expected1 = "happy";	
		Set<String> actual1 = md.signatureToWords("42779");	
		assertTrue(actual1.contains(expected1), "failure in test7.1: actual value does not equal the expected one");	
		// test a number String with some space	
		String expected2 = "happy";	
		Set<String>  actual2 = md.signatureToWords("42 7   7 9 ");	
		assertTrue(actual2.contains(expected2), "failure in test7.2: actual value does not equal the expected one");	
		// test a number String with some non-number characters	
		String expected3 = "happy";	
		Set<String>  actual3 = md.signatureToWords("HapPY42 7   7 9");	
		assertTrue(actual3.contains(expected3), "failure in test7.3: actual value does not equal the expected one");
		// test input a word(no numeric characters), it should return nothing
		Set<String>  actual4 = md.signatureToWords("hello");	
		assertTrue(actual4.isEmpty(), "failure in test7.4");	
	}
	
	
	// ----------------------------test8 tests makeDictionary in MapDictionary----------------------------
	@Test		
	public void test8() throws FileNotFoundException {
		Scanner in= new Scanner(new File(System.getProperty("user.dir")+"\\src\\words-test")); 		
		Map<String, List<String>> dictionary = new HashMap<String, List<String>>(); // Multi-key Map
		String dicSig;	
		String dicWord;		
		
		while(in.hasNextLine()){
			dicWord=in.nextLine().toLowerCase();
			dicSig=PredictivePrototype.wordToSignature(dicWord);
			if(PredictivePrototype.isValidWord(dicWord)) {
				List<String> words=dictionary.get(dicSig);
				if(words==null) {
					words=new ArrayList<String>();
					words.add(dicWord);
					dictionary.put(dicSig,words);
				}
				else {
					words.add(dicWord);
					dictionary.put(dicSig,words);
				}
			}        	
        }		
		in.close();  
//		System.out.println(dictionary);
		// test if the word "a" contains in the map
		List<String> actual1 = dictionary.get("2");	
		assertTrue(actual1.contains("a"), "failure in test8.1");	
		// test if the not valid word stored in the dictionary
		List<String> actual2 = dictionary.get("6333");
		assertTrue(actual2==null || actual2.size()==0 || !actual2.contains("nee5d") || !actual2.contains("need"), "failure in test8.2");	
	}
		
	
	// part4 TreeDictionary
	// ----------------------------test9 tests Trie class--------------------------------------------
	@Test		
	public void test9() throws FileNotFoundException {	
		// test if we can use the hasPrefix method to traverse all the words that match the input signature
    	Trie t=new Trie();
    	t.insert("2", "a");
    	t.insert("268", "ant");
    	t.insert("26237", "amber");
    	t.insert("269", "any");
    	t.insert("269663", "anyone");    	
    	ArrayList<String> actual = t.hasPrefix("26");
    	assertTrue(actual.contains("ant"), "failure in test9.1");	
    	assertTrue(actual.contains("amber"), "failure in test9.2");	
    	assertTrue(actual.contains("any"), "failure in test9.3");	
    	assertTrue(actual.contains("anyone"), "failure in test9.4");	
	}
	
	// ----------------------------test10 tests signatureToWords in TreeDictionary----------------------------
	@Test		
	public void test10() throws FileNotFoundException {	
		TreeDictionary td=new TreeDictionary(System.getProperty("user.dir")+"\\src\\words");
		// test a word happy with signature 42779		
		// test if we input complete signature 
		String expected1 = "happy";	
		Set<String> actual1 = td.signatureToWords("42779");	
		assertTrue(actual1.contains(expected1), "failure in test10.1");	
		// test if we input only a part of the signature	
		String expected2 = "hap";	
		Set<String> actual2 = td.signatureToWords("427");	
		assertTrue(actual2.contains(expected2), "failure in test10.2");	
		// test if we input the complete signature with some non-numeric characters	
		String expected3 = "happy";	
		Set<String> actual3 = td.signatureToWords("HapPY42 7   7 9");	
		assertTrue(actual3.contains(expected3), "failure in test10.3");
		// test input a word(no numeric characters), it should return nothing
		Set<String> actual4 = td.signatureToWords("hello");	
		assertTrue(actual4.isEmpty(), "failure in test10.4");	
	}
	
	// ----------------------------test11 tests trimWord in TreeDictionary----------------------------
	@Test		
	public void test11() throws FileNotFoundException {	
		TreeDictionary td=new TreeDictionary(System.getProperty("user.dir")+"\\src\\words-test");
		// test if the trim method can trim word "happy" correctly				
		String signature="42";
		ArrayList<String> words = new ArrayList<String>();
		words.add("happy");
		words.add("happily");
		Set<String> actual1 = td.trimWord(signature, words);		
		Set<String> expected1 = new HashSet<String>();
		expected1.add("ha");	
		assertTrue(equals(expected1,actual1), "failure in test11.1");
	}
	
	// helper method. Here we create a method to compare if 2 Set are the same
	public static boolean equals(Set<?> set1, Set<?> set2){
		// do not compare when the set is null
        if(set1 == null || set2 ==null){
            return false;
        }
        // do not compare when the sizes are different
        if(set1.size()!=set2.size()){
            return false;
        }
        // use containsAll to compare
        return set1.containsAll(set2);
    }
}