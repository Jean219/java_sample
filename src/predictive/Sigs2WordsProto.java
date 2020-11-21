package predictive;


import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Set;
import predictive.PredictivePrototype;

/**
 * In the Sigs2WordsProto class, we define the main method of signatureToWords in 
 * the PredictivePrototype class.
 * So that we can call the signatureToWords method using command line.
 * 
 * @author <Jing Meng>
 * @version 2020-02-18
 */
public class Sigs2WordsProto {
	public static void main(String[] args) throws FileNotFoundException {
		long startTime=System.currentTimeMillis();
		
		for(int i = 0;i<args.length;i++){
			Set<String> set = PredictivePrototype.signatureToWords(args[i]);
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
