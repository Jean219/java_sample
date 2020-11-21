package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
/**
 * This MapDictionary class is designed to solve the problem of the conversion 
 * between letters and numbers in nine-key input of mobile phone. Comparing with 
 * the methods mentioned in PredictivePrototype and ListDictionary, here we use 
 * map structure to store the dictionary information, so it's more efficient.
 * 
 * For each key-value pair, We store a signature as key, all words corresponding 
 * to the signature are stored as value. It's a multi-valued map。 We do not use
 * ArrayList<WordSig> here.
 * 
 * When a MapDictionary class object is created, it will get an initialised 
 * dictionary. And every time we invoke the signatureToWord method, it returns 
 * the value (List<String>) as set. We do not need to compare the signature with 
 * all the signatures stored in the dictionary line by line, so it's more time-saving
 * and efficient.
 * 
 * @author <Jing Meng>
 * @version 2020-02-18
 *
 */
public class MapDictionary implements Dictionary{
	private String path;
	private Map<String, List<String>> dictionary; 
		
	// constructor
	/**
	 * We initialise a dictionary for each MapDictionary object
	 * @param path the input variable
	 * @throws FileNotFoundException when the file cannot be found in the given path, throw one exception
	 */
	public MapDictionary(String path) throws FileNotFoundException {		
		this.path=path;	
		this.dictionary=makeDictionary(path);
	}
			
	/**
	 * SignatureToWords is a method implemented from the Dictionary interface. 
	 * As we use hashmap to store the signature-words information, we just need to 
	 * output the value of the key(input signature) in proper type.
	 * We save the words into a wordSet to avoid duplicate elements, and then 
	 * output the set.
	 */
	public Set<String> signatureToWords(String signature) {
		String regexNum="[^2-9]";		
		/*
		 * if the input signature not only contains numbers, we delete 
		 * anything that is not between 2 and 9
		 */
		String sig=signature.replaceAll(regexNum, " ").replace(" ", "");
		
		Set<String> wordSet = new HashSet<String>();
		/*
		 * If the signature matches at least 1 word, we add the words into the 
		 * wordSet, otherwise just return an empty set
		 */
		if(dictionary.get(sig) != null) {
			for(int i=0;i<dictionary.get(sig).size();i++) {
				wordSet.add(dictionary.get(sig).get(i));
			}
		}			
		
		return wordSet;
	}	
	
	// helper
	/**
	 * makeDictionary is a helper method, used to store all the signature-words 
	 * information as hashmap type. We can use one signature to get all the words 
	 * corresponding to it.
	 * @param path given path of the dictionary
	 * @return Map<String, List<String>> type dictionary
	 * @throws FileNotFoundException if the file can't be found in the given path, throw an exception
	 */
	public static Map<String, List<String>> makeDictionary(String path) throws FileNotFoundException{
		Scanner in= new Scanner(new File(path)); 		
		Map<String, List<String>> dictionary = new HashMap<String, List<String>>(); //Multi-key Map
		String dicSig;	
		String dicWord;		
		
		while(in.hasNextLine()){
			/*
			 * we use the PredictivePrototype method in PredictivePrototype to 
			 * get signature-word pairs line by line 
			 */
			dicWord=in.nextLine().toLowerCase();
			dicSig=PredictivePrototype.wordToSignature(dicWord);
			
			// only when the word is valid, we store the pair into dictionary
			if(PredictivePrototype.isValidWord(dicWord)) {
				// words is the word list corresponding to the signature
				List<String> words=dictionary.get(dicSig);
				/*
				 * if the word list is empty, we create a new List<String> to 
				 * the dictionary, else we get the list and put it back with one 
				 * more word
				 */
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
		
		return dictionary;
	}
	
	// the main method is used to simply check the 2 methods above
//	public static void main(String[] args) throws FileNotFoundException {
//		MapDictionary md=new MapDictionary(System.getProperty("user.dir")+"\\src\\words");
//		System.out.println(md.signatureToWords("25"));
//		System.out.println(md.signatureToWords("12500"));
//		System.out.println(md.signatureToWords("224"));
//		System.out.println(md.signatureToWords("1"));
//	}
}
