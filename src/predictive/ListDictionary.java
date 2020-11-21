package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
/**
 * This ListDictionary class is designed to solve the problem of the conversion 
 * between letters and numbers in nine-key input of mobile phone. Comparing with 
 * the methods mentioned in PredictivePrototype, methods in ListDictionary are more efficient.
 * 
 * Here we use 2 methods to convert between word and signature. The wordToSignature 
 * is what we defined in the PredictivePrototype class.But signatureToWords is a 
 * non-static method here, each time we use it, we use binarySearch to find the 
 * words in the dictionary whose signatures are the same with the input one. 
 * 
 * And we implement Dictionary interface, and use ArrayList<WordSig> type to store 
 * the dictionary. WordSig is a class we defined.
 * 
 * @author <Jing Meng>
 * @version 2020-02-10
 *
 */
public class ListDictionary implements Dictionary{

	private String path;
	private ArrayList<WordSig> dictionary; 
	
	/**
	 * getPath is a standard getter for ListDictionary class
	 * @return the path as String type
	 */
	public String getPath(){
		return this.path;
	}
	
	/**
	 * getDictionary is a standard getter for ListDictionary class
	 * @return the dictionary as ArrayList<WordSig> type
	 */
	public ArrayList<WordSig> getDictionary(){
		return this.dictionary;
	}
	
	// constructor
	/**
	 * we initialise a dictionary for each ListDictionary object
	 * @param path the input variable
	 * @throws FileNotFoundException when the file cannot be found in the given path, throw one exception
	 */
	public ListDictionary() throws FileNotFoundException {	
		// the path may need to change
		this.path=System.getProperty("user.dir")+"\\src\\words";
//		this.path="/usr/share/dict/words";
		this.dictionary=makeDictionary(path);
	}	
		
	
	/**
	 * signatureToWords is a method implemented from the Dictionary interface. 
	 * We use binarySearch to search the input signature in the dictionary.
	 * Just like the method in PredictivePrototype, it only compares the regularized 
	 * signatures and outputs the regularized words set.
	 * When we use the binarySearch method, it only return the first match index.
	 * So after we get the return value, we need to look forward and look back, 
	 * to get all the words with the same signature.	
	 */
	public Set<String> signatureToWords(String signature) {
		Set<String> set = new HashSet<String>();
			
		// regularize the input, leaving only the numeric letters between 2 and 9 
		String regexNum="[^2-9]"; 				
		signature=signature.replaceAll(regexNum, " ").replace(" ", "");
		
		/*
		 * As the dictionary stores ArrayList<WordSig> type information, we need 
		 * to create a ArrayList<String> type list to store its signatures. So 
		 * that we can use binary search method to search String type signature 
		 * in the same type list (ArrayList<String>) later
		 */
		ArrayList<String> list=new ArrayList<String>();
		for(int i=0;i<dictionary.size();i++) {
			list.add(dictionary.get(i).getSignature());
		}
		
		/* we use binary search method to look for the index of the value.
		 * Here we use variable i and j to look forward/backward (from the return index)
		 * to get all the words with the same index, and store them into the output set.
		 */
		int i= Collections.binarySearch(list, signature);
		int j=i;

		while(i>-1 &&i<dictionary.size()&& signature.compareTo(dictionary.get(i).getSignature())==0) {
			set.add(dictionary.get(i).getWord());
			i++;
		}
		
		while(j>-1 &&j<dictionary.size() && signature.compareTo(dictionary.get(j).getSignature())==0) {
			set.add(dictionary.get(j).getWord());
			j--;
		}
		
		return set;
	}		

	// helper
	/**
	 * makeDictionary is a helper method, used to store all the word-signature 
	 * pair of the given path dictionary, into ArrayList<WordSig> type.
	 * @param path given path of the dictionary
	 * @return ArrayList<WordSig> type dictionary
	 * @throws FileNotFoundException if the file can't be found in the given path, throw an exception
	 */
	public static ArrayList<WordSig> makeDictionary(String path) throws FileNotFoundException{
		Scanner in= new Scanner(new File(path)); 		
		String dicWord;				
		ArrayList<WordSig> dictionary=new ArrayList<WordSig>();
		
		while(in.hasNextLine()){
			dicWord=in.nextLine();	
			// regularize all the non-letter digits, and lowercase all the letters
			if(PredictivePrototype.isValidWord(dicWord)) {
				// lowercase the word, and store the word-signature pair into the dictionary
				dictionary.add(new WordSig(dicWord.toLowerCase(), PredictivePrototype.wordToSignature(dicWord)));      
			}   
        }
		
		in.close();  
		
		// we use comparator to sort the dictionary in ascending signature order
		Comparator<WordSig> mySort = (e1, e2) -> e1.getSignature().compareTo(e2.getSignature());
		Collections.sort(dictionary, mySort);
//		dictionary.sort(mySort);
		
		return dictionary;
	} 
		
	// the main method is used to simply check the 2 methods above
	public static void main(String[] args) throws FileNotFoundException{ 
		ListDictionary ld=new ListDictionary();
		System.out.println(ld.getDictionary().size());
		System.out.println("114 "+ld.getDictionary().get(114).getWord());
		System.out.println("115 "+ld.getDictionary().get(115).getWord());
		System.out.println("116 "+ld.getDictionary().get(116).getWord());
		System.out.println("117 "+ld.getDictionary().get(117).getWord());
		System.out.println("118 "+ld.getDictionary().get(118).getWord());
		System.out.println("119 "+ld.getDictionary().get(119).getWord());
		System.out.println(ld.signatureToWords("32190"));
		System.out.println(ld.signatureToWords("42 7   7 9 "));
		System.out.println(ld.signatureToWords("42779"));
		ArrayList<WordSig> d=new ArrayList<WordSig>();
		d.add(new WordSig("Hello".toLowerCase(), PredictivePrototype.wordToSignature("Hello"))); 
		System.out.println(d);
	}
}
