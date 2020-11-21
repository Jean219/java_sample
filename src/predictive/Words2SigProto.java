package predictive;

/**
 * In the Words2SigProto class, we define the main method of wordToSignature in 
 * the PredictivePrototype class.
 * So that we can call the wordToSignature method using command line.
 * 
 * @author <Jing Meng>
 * @version 2020-02-10
 */
public class Words2SigProto {
	public static void main(String[] args) {
		for(int i = 0;i<args.length;++i){
			System.out.println(PredictivePrototype.wordToSignature(args[i]));
		}
	}
}
