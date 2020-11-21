package predictive;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Set;
/**
 * The Sigs2WordsTree is used to invoke the signatureToWords method in TreeDictionary.
 * So that we can call the signatureToWords method using command line.
 * 
 * Comparing with Sigs2WordsMap, we can get all the possible words that contain 
 * the input input prefix, with length limitation of the input signature's length.
 * 
 * When comparing the efficiency of Sigs2WordsMap and Sigs2WordsTree, we always 
 * see the map method runs faster. 
 * But both methods are highly efficient. As the input arguments increase, the 
 * running time does not change much。
 * @author <Jing Meng>
 * @version 2020-02-14
 */
public class Sigs2WordsTree {
	public static void main(String[] args) throws FileNotFoundException {
		long startTime=System.currentTimeMillis();
		
		TreeDictionary td=new TreeDictionary(System.getProperty("user.dir")+"\\src\\words");
//		TreeDictionary td=new TreeDictionary("/usr/share/dict/words");
		
		for(int i = 0;i<args.length;++i){
			Set<String> set = td.signatureToWords(args[i]);
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
