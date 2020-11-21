package predictive;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Set;
/**
 * The Sigs2WordsMap is used to invoke the signatureToWords method in MapDictionary.
 * So that we can call the signatureToWords method using command line.
 * 
 * Comparing with Sigs2WordsList, we need to edit a path for the MapDictionary object.
 * 
 * @author <Jing Meng>
 * @version 2020-02-14
 */
public class Sigs2WordsMap {
	public static void main(String[] args) throws FileNotFoundException {
		long startTime=System.currentTimeMillis();
		
		MapDictionary md=new MapDictionary(System.getProperty("user.dir")+"\\src\\words");
//		MapDictionary md=new MapDictionary("/usr/share/dict/words");
		
		for(int i = 0;i<args.length;++i){
			Set<String> set = md.signatureToWords(args[i]);
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
