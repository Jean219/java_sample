package predictive;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * This PredictivePrototype class is designed to solve the problem of the conversion 
 * between letters and numbers in nine-key input of mobile phone.
 * Here we define 2 methods: wordToSignature is used to transform word to number, while
 * signatureToWords is used to transform signature to words. Numbers and words are 
 * both represented as String type variables.
 * The 2 methods are both static methods.
 * 
 * @author <Jing Meng>
 * @version 2020-02-10
 */
public class PredictivePrototype {

	/**
	 * wordToSignature method is used to transform word to number。The input may 
	 * contain not only letters, but also any characters. We will regularize all 
	 * non-letter char into space, and lowercase the letters. In the end, delete 
	 * all the space, and letter by letter to make our output number String.
	 * 
	 * As we read the input word letter by letter. We use StringBuffer instead 
	 * of String type to store the output signature.
	 * The main difference between the String type and the StringBuffer type is 
	 * that Strings are "constant" objects. Each time we change the value of String, 
	 * we need to store it in another address and get a new String object.
	 * So when the object changes frequently, there are too many unreferenced 
	 * objects in memory, and it would have an bad impact on System performance. 
	 * Although the JVM's GC can deal with the unreferenced objects and addresses,
	 * it would be very slow.
	 * But if we use the StringBuffer class, the result is different. Each time 
	 * the result operates on the StringBuffer object itself, instead of generating 
	 * a new object and changing the object reference.
	 * As a conclusion, it's better to use StringBuffer when the String objects change frequently.
	 * 
	 * @param word the input variable. 
	 * @return the output number String
	 */	
	public static String wordToSignature(String word) {
		// we use Hashmap to memorize the relation between letter and number
		HashMap<Character,Integer> map = new HashMap<Character,Integer>(); 
		map.put('a', 2);
		map.put('b', 2);
		map.put('c', 2);
		map.put('d', 3);
		map.put('e', 3);
		map.put('f', 3);
		map.put('g', 4);
		map.put('h', 4);
		map.put('i', 4);
		map.put('j', 5);
		map.put('k', 5);
		map.put('l', 5);
		map.put('m', 6);
		map.put('n', 6);
		map.put('o', 6);
		map.put('p', 7);
		map.put('q', 7);
		map.put('r', 7);
		map.put('s', 7);
		map.put('t', 8);
		map.put('u', 8);
		map.put('v', 8);
		map.put('w', 9);
		map.put('x', 9);
		map.put('y', 9);
		map.put('z', 9);
			
		// lowercase all the letters
		char[] charArray;
		int[] sig;
		
		// If there is no char after regularization, we stop, and return "" straightly.
		if(isValidWord(word)) {
			// make the input word String into character array
			charArray = word.toLowerCase().toCharArray();
			sig=new int[word.length()];	
			
			for(int i=0;i<word.length();i++) {
				// each lowercase letter corresponds to a number stored in the map
				sig[i]=map.get(charArray[i]);
			}
			// here we use StringBuffer to store the output signature
			StringBuffer signature = new StringBuffer();
			for(int i=0;i<sig.length;i++) {
				signature.append(sig[i]);
			}
			
			return signature.toString();
		}
		else {
			return "";
		}				
	}	

	/**
	 * signatureToWords method is used to transform number to word. Here we scan 
	 * the relations between word and signature of a given dictionary, and then
	 * compare the input signature with the relations one by one.
	 * We only compare the relation between non-letter signature and non-numerical word.
	 * So we need to regularize the words in dictionary, input signature respectively.
	 * 
	 * We are not storing the dictionary here. Instead, for each input signature, 
	 * we do a line-by-line scan of the dictionary. Because sometimes the given 
	 * dictionary may be very large, it will not be efficient to store all the words 
	 * or relations in advance (maybe many unnecessary information). 
	 * If we compare each line of the dictionary with the given signature, we only 
	 * need to store the words whose signature match the input variable. It saves space.
	 * 
	 * When the signature contains some non-numeric characters, we delete them and 
	 * them match it with the dictionary. For example, 42779 and 4277910 should 
	 * output the same word set.
	 * 	
	 * @param signature the input variable. 
	 * @return the output word String, as set type
	 * @throws FileNotFoundException if we cannot find a file from the given path, we throw an exception
	 */
	public static Set<String> signatureToWords(String signature) throws FileNotFoundException{
		// the path may need to change
		Scanner in= new Scanner(new FileReader(System.getProperty("user.dir")+"\\src\\words")); 		
//		Scanner in= new Scanner(new FileReader("/usr/share/dict/words"));
		String dicWord;		
		
		// regularize the input, leaving only the numeric letters between 2 and 9 
		String regexNum="[^2-9]"; 				
		signature=signature.replaceAll(regexNum, " ").replace(" ", "");
		
		Set<String> set = new HashSet<String>();

		// if the signature contains no numeric-character, return an empty set
		if(!signature.isEmpty()){
			while(in.hasNextLine()){
				/*
				 * Because the output words are all lowercase word with no non-letter 
				 * character, we need to regularize the dicWord variable
				 */
				dicWord=in.nextLine();
	        	String dicSignature=wordToSignature(dicWord);
	    		/*
	    		 * scan line by line, and put the record into the output set when the 
	    		 * word's signature in dictionary matches the signature
	    		 */
	    		if(dicSignature.compareTo(signature)==0) {
	    			set.add(dicWord.toLowerCase());
	    		}
	        }
	        in.close();
		}        
        return set;
	}
		
	/**
	 * isValidWord is one method to return a boolean value, if the input word 
	 * contains any non-alphabetic characters. If it contains at least 1 non-alphabetic 
	 * character, then return false. Else it returns true to represent the input 
	 * String is a word.
	 * 
	 * @param word the input as String type
	 * @return a boolean value
	 */
	public static boolean isValidWord(String word) {
		// if there is no character between 2 and 9, return false
		if(word.length()==0) {
			return false;
		}
		for(int i=0;i<word.length();i++) {	
			// the result of (charAt('a')-'a') should between 0 and 25
			if(word.toLowerCase().charAt(i)-'a'>25 ||word.toLowerCase().charAt(i)-'a'<0) {
				return false; 	
			}									
		}		
		return true;
	}
	
	
	// the main method is used to simply check the 2 methods above
//	public static void main(String[] args) throws FileNotFoundException{
//		// test if we can get a word from a "wrong" number
//		System.out.println(signatureToWords("1"));
//		System.out.println(signatureToWords("329hello"));
//		System.out.println(signatureToWords("hello"));
//		System.out.println(signatureToWords("329"));
//		System.out.println(isValidWord("happy"));
//		System.out.println(isValidWord("hapYpy"));
//		System.out.println(isValidWord("hap py"));
//		System.out.println(wordToSignature("happy"));
//		System.out.println(isValidWord(""));
//	}
}

