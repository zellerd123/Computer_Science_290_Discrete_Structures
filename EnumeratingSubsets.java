import java.util.*;

/**
 * A collection of functions related to the generation and analysis of
 * Powersets.  Will ultimately be used in an experiment to analyze the growth
 * of Powersets of Sets of various cardinalities.
 *
 * Colgate University COSC 290L
*/
public class EnumeratingSubsets {
    
    /**
     * Given a set s, containing elements of type String, return the powerset of s
     * Must not modify the original argument Set s
     * @param s a set of elements
     * @return the set of all subsets of input set s
     */
    public static Set<Set<String>> generatePowerset(Set<String> s) {                    
         
                
        //what should your base case(s) be?  For what argument(s) would you not need to
        //do any recursion to know your answer?
        //[Base case(s) go here...]
        if (s.size() == 0){
            Set<String> emptyS = new HashSet<String>();
            Set<Set<String>> emptySoS = new HashSet<Set<String>>();
            emptySoS.add(emptyS);
            return emptySoS;
        }
        
        //removing an element from s seems reasonable, as we will pass in a slightly smaller set
        //on subsequent recursive calls... but I'm doing something dangerous here! read the docstring!
        String removed = removeAnyElement(s); 
        //Let's treat our recursive function as a black-box - pretend it works perfectly and gives
        //us back exactly what we want - what will be returned by this recursive call?
        //Hint: this gives us all subsets of s without.......?
        Set<Set<String>> subsetsWithoutRemoved = generatePowerset(s);
        Set<Set<String>> finalSoS = new HashSet<Set<String>>();
        for (Set<String> set : subsetsWithoutRemoved){
        		 finalSoS.add(set);
        		 Set<String> finalSet = new HashSet<String>(set);
        		 finalSet.add(removed);
        		 finalSoS.add(finalSet);
        }

        s.add(removed);

       
        return finalSoS;
    }
    
    
    
    /**
     * Given a Set of sets of Strings s, returns a fixed length array of ints
     * indicating the count of subsets of each particular cardinality in s.
     * For example, in the returned array, the value of index 1 represents the 
     * number of subsets in s with a cardinality of 1, and so on.
     * Example: an s of { {"a"}, {"b","c"}, {"d","e"}, {"f","g","h","i"} } would return
     * the array: {0, 1, 2, 0, 1}
     * @param s a set of sets of Strings
     * @return an int[] indicating the counts of subsets of each cardinality in s
     */        
    public static int[] countCardinalities(Set<Set<String>> s){
    int biggest = 0; //saves the biggest so that the array can be made the proper size
    
        for (Set<String> ss : s){
        		if (ss.size() > biggest) biggest = ss.size();
        }
   	int[] k = new int[biggest + 1];//creates an array 1 bigger than the biggest to include zero
        for(Set<String> ss : s){ //parses through the set of sets and finds the size, then adds accordingly
        	int size = ss.size();  
        	k[size] += 1;
        }
        return k; //returns the array that contains the accurate sizes
     }    
    
    
    
    /**
     * Given a set s, containing elements of type String, removes and returns 
     * one String from the set
     * @param s a set of elements
     * @return the String removed from set s
     */    
    public static String removeAnyElement(Set<String> s){
        if (s.size() == 0) 
            throw new NoSuchElementException("Cannot remove from empty set!");
        String removed = s.iterator().next();
        s.remove(removed);
        return removed;        
    }    
    
    

    
    

    
}
