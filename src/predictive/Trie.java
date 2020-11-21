package predictive;

import java.util.List;
import java.util.ArrayList;

/**
 * This class is used to create Trie structure dictionary information. This is a 
 * octree, each node stores one digit number, 8 subnodes(2-9), if it's End of one word, 
 * whether at least one of the child nodes has a value, and a word list up to this node.
 * 
 * The root node has no value.
 * Each time we insert one word, we use insert method to store its signature and word.
 * 
 * @author <Jing Meng>
 * @version 2020-02-14
 *
 */
public class Trie{
	
    private final static int SIZE=8;
    private TrieNode root;// the root of the trie structure dictionary
    
    // constructor of Trie, initialise one root node for each Trie object
    public Trie(){
        root=new TrieNode();
    }

    /**
     * TrieNode is a private class of Trie, used to create Trie node in a Trie 
     * structure object.
     * For each node, we have the node's value, TrieNode[] of its son, if it's 
     * End of one word, whether at least one of the child nodes has a value, 
     * and a word list up to this node.
     */
    private class TrieNode {
    	private char val; 
        private TrieNode[] son; 
        private boolean isEnd; // the node is the last char of at least one word
        private boolean hasSon;
        private List<String> words;
        
        // constructor of TrieNode, initialise all the node attributes
        private TrieNode(){
            son=new TrieNode[SIZE]; 
            isEnd=false;
            hasSon=false;   
            words=new ArrayList<String>();
        }
    }

	/**
     * insert method is used to insert new word into the Trie structure dictionary. 
     * Both signature and word are input arguments.
     * @param signature the input signature as String
     * @param word the input word as String
     */
    public void insert(String signature, String word){
        if(signature==null||signature.length()==0){
            return;
        }
        TrieNode node=root;
        char[] sigs=signature.toCharArray();
		
        // i can be seen as depth, we traverse the child nodes on each layer 
		for(int i=0; i<sigs.length; i++){
            int pos=sigs[i]-'2';
            // if it has no child node value before, reset the child's value and attributes
            if(node.son[pos]==null){
                node.hasSon = true;
                node.son[pos]=new TrieNode();
                node.son[pos].val=sigs[i];
            }            
            // then move down and process the child nodes
            node=node.son[pos];
        }
		// when we get to the last char's node, reset isEnd as true, and put the 
		// word into the word list
        node.isEnd=true;
        node.words.add(word);
    }
    
    /**
     * hasPrefix method is used to input one prefix, and return a word list as 
     * ArrayList<String> that all contain the prefix 
     * @param contain the prefix part as String
     * @return wordlist as ArrayList<String>
     */
    public ArrayList<String> hasPrefix(String contain){
        if (contain == null || contain.length() == 0){
            return null;
        }
        TrieNode node = root;
        char[] pres = contain.toCharArray();
        for (int i = 0; i < pres.length; i++){
        	/*
        	 * pos starts from the child node of the root (assume the inputs are 
        	 * all numbers, which meed the precondition)
        	 */
        	int pos = pres[i] - '2';
            if (node.son[pos] == null){ 
            	// if the index cannot get the child's position, it might be wrong
            	// return nothing
            	return null;            	
            }
            else {
            	// keep moving down, until all the characters in the input signature can find its node
            	node = node.son[pos];
            }
        }
        /*
         * when we find the highest node (matches the last character of the input 
         * signature), we use the helper method: preTraverse to traverse all the 
         * words following the node
         */
        ArrayList<String> str=new ArrayList<String>();        
        return preTraverse(node,contain,str); 
    }

    // helper
    /**
     * preTraverse method is used to help hasPrefix pretraverse all the words 
     * that contain specific prefix part.
     * @param node the node begins to look for full word
     * @param contain the prefix as String
     * @param str word list as ArrayList<String>
     * @return one word list that has the same prefix as ArrayList, no limit about the words' length 
     */
    public ArrayList<String> preTraverse(TrieNode node, String contain,ArrayList<String> str){        
        /*
    	 * Whenever there is a word at the end of the node, print it.
    	 * If we can not find a word that matches the signature, return nothing
    	 */
        if(node.isEnd) {
        	// when we find one node with isEnd attribute true, output the node's word list
        	str.addAll(node.words);
        }
        
        if(node.hasSon) {
        	for(int i=0;i<node.son.length;i++) {
        		if(node.son[i]!=null) {
        			preTraverse(node.son[i],contain+node.son[i].val,str);
        		}
        	}
        }
		return str;        
    } 
         
    // main method is used to test
//    public static void main(String[] args) {
//    	Trie t=new Trie();
//    	t.insert("2", "a");
//    	t.insert("268", "ant");
//    	t.insert("26237", "amber");
//    	t.insert("269", "any");
//    	t.insert("269663", "anyone");    	
//    	System.out.println(t.hasPrefix("26"));    	
//    }
}