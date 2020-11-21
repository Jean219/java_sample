package predictive;

import java.util.Set;

/**
 * Dictionary is a interface, only contain one method: signatureToWords.
 * By implementing the interface, we need to input a signature as String type, 
 * and return a word set that contains all the alternative words as Set<String> type
 * 
 * @author <Jing Meng>			
 * @version 2020-02-18	
 */
public interface Dictionary {
	/**
     *  A signatureToWords method for dictionary class to get the word set by 
     *  inputing a signature.
     *  @return The word set corresponding to the signature as Set<String> type
     */
	public Set<String> signatureToWords(String signature);

}
