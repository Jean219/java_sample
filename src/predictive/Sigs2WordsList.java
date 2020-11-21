package predictive;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Set;
/**
 * The signatureToWords method in ListDictionary is different from the one in the 
 * PredictivePrototype. As every time we create a ListDictionary object, we 
 * initialize one dictionary for the object, it might take some time when we create 
 * the object, but after that, it saves time when we use the dictionary to look 
 * for some word-signature pairs.
 * 
 * Comparing the 2 signature2WordsList, we find that if the input arguments are 
 * not too many (like 1 or 2), the PredictivePrototype.signatureTowords is more 
 * efficient, because most time are taken in the creating part. But 
 * ListDictionary.signaturesToWords will be more efficient as the number of 
 * argument increasing. Detailed screenshots can be found in the uploaded files.
 * 
 * Because I write my code in windows system, there are no time function in cmd.
 * I just write some code in the method below, to help me know how much time the 
 * method takes. 
 * 
 * @author <Jing Meng>
 * @version 2020-02-10
 */
public class Sigs2WordsList {
	public static void main(String[] args) throws FileNotFoundException {
		long startTime=System.currentTimeMillis(); 
		
		ListDictionary ld=new ListDictionary();
		
		for(int i = 0;i<args.length;++i){
			Set<String> set = ld.signatureToWords(args[i]);
			String out = "";

			Iterator<String> iterator = set.iterator();
			while(iterator.hasNext()) {
				out +=" "+iterator.next(); 
			}
			System.out.println(args[i]+" : "+out);
		}
		// output the running time of the program
		long endTime=System.currentTimeMillis();
		System.out.println("running time period :"+(endTime-startTime)+"ms");
	}
}
