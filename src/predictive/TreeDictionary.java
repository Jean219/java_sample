package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * This TreeDictionary class is designed to solve the problem of the conversion 
 * between letters and numbers in nine-key input of mobile phone. Comparing with 
 * the methods mentioned in PredictivePrototype, methods in ListDictionary are more efficient.
 * 
 * Here we use 2 methods to convert between word and signature. The wordToSignature 
 * is what we defined in the PredictivePrototype class.But signatureToWords is a 
 * non-static method here, each time we use it, we use binarySearch to find the 
 * words in the dictionary whose signatures are the same with the input one. 
 * 
 * And we implement Dictionary interface, and use Trie structure to store the dictionary. 
 * Trie is a class we defined in the Trie.java file.
 * 
 * @author <Jing Meng>
 * @version 2020-02-18
 *
 */
public class TreeDictionary implements Dictionary{
	private String path;
	private Trie dictionary; 
	
	// constructor
	public TreeDictionary(String path) throws FileNotFoundException {		
		this.path=path;	
		this.dictionary=makeDictionary(path);
	}			
	
	/**
	 * SignatureToWords is a method implemented from the Dictionary interface. 
	 * Here we use the hasPrefix method in Trie class to get a word list that 
	 * all the words match the input prefix as ArrayList<String>, and then use 
	 * trimWord method to limit the words' length with the input signature's length.
	 */
	public Set<String> signatureToWords(String signature) {		
		String regexNum="[^2-9]"; 		
		signature=signature.replaceAll(regexNum, " ").replace(" ", "");
							
		ArrayList<String> words;
		
		/*
		 * only when the regularized signature has at least 1 numeric character, 
		 * will we match it with the dictionary information
		 */
		if(signature.isEmpty()){
			words = new ArrayList<String>();
		}
		else {
			words = dictionary.hasPrefix(signature);
		}
		
		return trimWord(signature,words);		
	}	

	// helper method: make a dictionary
	/**
	 * makeDictionary is a helper method, used to insert all the signature-word
	 * information into a Trie structure.
	 * @param path given path of the dictionary
	 * @return Map<String, List<String>> type dictionary
	 * @throws FileNotFoundException if the file can't be found in the given path, throw an exception
	 */
	public static Trie makeDictionary(String path) throws FileNotFoundException{
		Scanner in= new Scanner(new File(path)); 		
		Trie dictionary=new Trie();	
		String dicSig;	
		String dicWord;	
		
		while(in.hasNextLine()){
			dicWord=in.nextLine().toLowerCase();
			dicSig=PredictivePrototype.wordToSignature(dicWord);
			
			if(PredictivePrototype.isValidWord(dicWord)) {
				dictionary.insert(dicSig,dicWord);
			}   
        }		
		in.close();
				
		return dictionary;
	}
	
	// helper
	/**
	 * trimWord is a helper method, used to limit the words' length with the input signature. 
	 * @param signature the input signature as String type
	 * @param words the input word list as ArrayList<String>
	 * @return a set of words whose length is limited
	 */
	public Set<String> trimWord(String signature, ArrayList<String> words){
		Set<String> set = new HashSet<String>();	
		
		if(words.size()==0) {
			return set;
		}
		
		for(int i=0;i<words.size();i++) {
			// here we limit the length of the word
			set.add(words.get(i).substring(0,signature.length()));
		}
		return set;
	}
	
	// main method is used to do simple test
	public static void main(String[] args) throws FileNotFoundException {
		TreeDictionary td=new TreeDictionary(System.getProperty("user.dir")+"\\src\\words-test");
		System.out.println(td.signatureToWords("142779"));
		System.out.println(td.signatureToWords("2"));
		// trimWord
		ArrayList<String> words=new ArrayList<String>();
		words.add("any");
		System.out.println(td.trimWord("2",words));
	}	
}