package predictive;

/**
 * WordSig is a class defined to store word-signature pairs. Here words and signatures 
 * are both String types.
 * 
 * @author <Jing Meng>
 * @version 2020-02-18
 *
 */
public class WordSig implements Comparable<WordSig>{
	private String word;
	private String signature;
	
	//constructor
	public WordSig (String word,String signature) {
		this.word = word;
		this.signature = signature;
	}
	
	/**
	 * getWord is a method to get the word attribute of a WordSig type object 
	 * @return the word as String type
	 */
	public String getWord() {
		return this.word;
	}
	
	/**
	 * getWord is a method to get the signature attribute of a WordSig type object
	 * @return the signature as String type
	 */
	public String getSignature() {
		return this.signature;
	}
	
	
	@Override
	public int compareTo(WordSig other) {
		// we compare the signatures of different words, so that we can sort the 
		// list by signature order
		return this.getSignature().compareTo(other.getSignature());
	}	  
}