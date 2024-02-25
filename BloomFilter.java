/**
 * Abstraction of a bloom filter
 * Methods for inserting/looking up String elements utilizing a variable
 * hashing algorithm (an implementation the included BFHash interface).
 *
 * Colgate University COSC 290L
 * Updated 2022
 */

import java.util.*;


public class BloomFilter {
    
    private final int k;  // number of hash functions
    private BFHash filterHash;  // hash family from which hash functions are obtained
    private boolean[] bitArray;
    
    private static final BFHash SIMPLE_HASH = new SimpleHash();
    private static final BFHash SMART_HASH = new AdvancedHash();
    
    //used in "generate universe", but might be useful to you too!
    private static final Random rand = new Random();
    private static final int MAX_UNIVERSE_STR_LEN = 10;
    private static final int MIN_UNIVERSE_STR_LEN = 3;
    
    
    /**
     * Makes a new bloom filter with m slots and k hash functions from the specified hash family.
     * @param m number of slots in array
     * @param k number of hash functions to use when inserting/looking up elements
     * @param filterHash hash family from which to draw hash functions
     */
    public BloomFilter(int m, int k, BFHash filterHash) {
        this.k = k;      
        this.filterHash = filterHash;
        this.bitArray = new boolean[m];
        
    }
    
    
    //Adds element to the Bloom Filter
    //Implement me for Part 4.2
    public void addToFilter(String element) {
        for(int i = 0; i < k; i++)
        {
        	int hash1 = Math.abs(filterHash.getHashCode(element,i));
        	bitArray[hash1 % bitArray.length] = true;
        }
    }
    
    //Implement me for Part 4.3!
    //Performs a lookup of item using the reference Bloom Filter
    //returns true if item "might be" in the set
    //returns false if item "definitely is not" in the set
    public boolean contains(String element) {  
    	for(int i = 0; i < k; i++)
        {
        	int hash1 = Math.abs(filterHash.getHashCode(element,i));
        	if (bitArray[hash1 % bitArray.length] == false)
        	{
        		return false;
        	}
        } 	
        return true; 
    }    
    
    
    //Finish me for Part 4.4
    //Recreate the example from Part 2.3 of the lab writeup here
    //Use print statements each step of the way to track your progress
    //Some code is provided for you, you'll need to fill in the rest
    public static void runLabExample(){
        
        //are these values correct?
        int m = 10;        
        int k = 3;  
        
        BloomFilter testFilter = new BloomFilter(m, k, SIMPLE_HASH); //what's wrong here?
        
        testFilter.addToFilter("dog");
        System.out.println(testFilter); //what do you expect here per the diagrams in 2.3?
        
        testFilter.addToFilter("cat");
        System.out.println(testFilter);
        
        System.out.println("lookup \"dog\"?: " + testFilter.contains("dog"));
        System.out.println("lookup \"cat\"?: " + testFilter.contains("cat"));
        System.out.println("lookup \"goose\"?: " + testFilter.contains("goose"));   
        System.out.println("lookup \"groud hog\"?: " + testFilter.contains("ground hog"));
        
    }
    
    
    
    //Creates a randomly generated universe of cardinality size
    public static Set<String> generateUniverse(int size){
        if (size > Integer.MAX_VALUE)
            throw new RuntimeException("Requested universe size too big per the max string length!");
        
        Set<String> universe = new HashSet<String>();
        while (universe.size() < size){
            int strLen = rand.nextInt(MAX_UNIVERSE_STR_LEN)+ MIN_UNIVERSE_STR_LEN;
            StringBuilder randStr = new StringBuilder();
            for (int i = 0; i < strLen; i++){
              int ch = rand.nextInt('Z' - 'A' + 1);
              //coinflip -- upper or lower case?
              if (rand.nextInt(2) == 1)
                ch += 'a';
              else
                ch += 'A';
              randStr.append((char)ch);
            }
            universe.add(randStr.toString());

        }
        return universe;
    }
    
     
    //Implement me for Part 4.5
    //returns false positivity rate for a bloom filter with the provided attributes/hash function
    public static double checkFalsePosRate(BFHash filterHash, int m, int k, int n){
    	Set<String> universal = generateUniverse(2*n);
    	BloomFilter posRate = new BloomFilter(m, k, filterHash);
    	int count = 0;
    	int falsePositive = 0;
    	
    	for(String a : universal)
    	{
    		if(count <= n)
    			posRate.addToFilter(a);
    		else
    		{
    			if(posRate.contains(a))
    				falsePositive++;
    		}
    		count++;
    	}
    	
    	double rate = ((double)falsePositive)/n;
    	return rate;
    }
    
    
    
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bitArray.length; i++){
            if (bitArray[i])
                sb.append(i + ",");
        }
        if (sb.length() > 0)
            sb.setLength(sb.length()-1);
        return "Bloom Filter Flipped Bits (0-" + bitArray.length + "): {"+ sb + "}";  
    }
    
}
