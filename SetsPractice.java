import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

//Paige Blum and Doug Zeller

/**
 * Contains two standalone functions for working with Sets... warmup for next week
 * where we will leverage recursion to perform some PowerSet operations
 *
 * Colgate University COSC 290L
 * 
 */
public class SetsPractice {
    
    
    
    /**
     * Perform set union, producing a new set.
     * Does not modify either argument Set
     * @param s1 one of the sets to be unioned
     * @param s2 the other set to be unioned
     * @return a new set equal to the union of s1 and s2
     */
    public static Set<String> generateUnion(Set<String> s1, Set<String> s2) {
        Set<String> unionSet = new HashSet<String>();
        int size1 = s1.size();
        int size2 = s2.size();
        
        //check for empty set
        if(s1.isEmpty() == true || s2.isEmpty() == true)
        	return unionSet;
        	
        
        //iterate depending on which set's cardinality is higher
        if(size1 >= size2)
        {
			for(String value : s1)
			{
				if(s2.contains(value) == true)
				{
					unionSet.add(value);
				}
			}
		}
		else
		{
			for(String value : s2)
			{
				if(s1.contains(value) == true)
				{
					unionSet.add(value);
				}
			}
		}
        return unionSet;  	
    }
    
    
    
    /**
     * Perform set intersection, producing a new set.
     * Does not modify either argument Set
     * @param s1 one of the sets to be intersected
     * @param s2 the other set to be intersected
     * @return a new set equal to the intersection of s1 and s2
     */
    public static Set<String> generateIntersection(Set<String> s1, Set<String> s2) {
        Set<String> interSet = new HashSet<String>();
        int size1 = s1.size();
        int size2 = s2.size();
        
        //check for empty set
        //if(s1.isEmpty() == true || s2.isEmpty() == true)
        	//return interSet;
        	
        
       
		for(String value : s1)
			{
				if(s2.contains(value) == false)
				{
					interSet.add(value);
				}
			}

	
		for(String value : s2)
			{
				if(s1.contains(value) == false)
				{
					interSet.add(value);
				}
			}
			
        return interSet;  	 
    }
    
    /**
     * Checks to see if either argument set is a subset of the other.
     * Does not modify either argument Set.
     * @param s1 the first set in the comparison
     * @param s2 the second set in the subset comparison
     * @return true if s1 is a subset of s2 or s2 is a subset of s1.
     * Also returns true if both sets are subsets of one another.
     */
    public static boolean isEitherSubset(Set<String> s1, Set<String> s2) {
    	if(s1 == null || s2 == null)
    		return true;
    	else if(s1.containsAll(s2))
    		return true;
    	else if(s2.containsAll(s1))
    		return true;
    	else
    		return false;
         
    }       
    

    
    /**
     * Checks to see if ALL subsets in the argument set of sets
     * contain the argument element.  Returns false given an argument
     * set of size 0 (i.e. contains no subsets)
     * @param sets a set of sets of Strings
     * @param an element to check
     * @return a boolean indicating of all subs contain the element
     */  
    public static boolean allSubsContain(Set<Set<String>> sets, String element){
        
        if(sets.size() == 0)
        	return false;
        
    	for(Set set : sets)
    	{
    		if(set.contains(element) == false)
    		{
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    
    
    /**
     * Removes and returns one element from the argument Set
     * It doesn't matter which element is removed; any will suffice
     * @param set of Strings
     * @return the String removed
     * @throws java.util.NoSuchElementException if given an empty Set
     */  
    public static String removeAnyElement(Set<String> s){
    	if(s.size() == 0)
    		throw new java.util.NoSuchElementException("Is an empty set! Can not remove an element");
    	
    	Object [] removeE = s.toArray();
    	String toRemove = removeE[0].toString();
    	s.remove(toRemove);
    	return toRemove;
    }
    
    
    
    /**
     * Given string x and a set of sets, {S1, S2, ..., Sk} return a new set of
     * sets such that it contains the same sets as the input, but with x removed.
     * Note: the original inputs should NOT be modified.
     * @param sets a set of sets of Strings
     * @param x element to remove
     * @return the set of all subsets of input set s
     */
    public static Set<Set<String>> removeFromAllSubs(Set<Set<String>> sets, String toRemove) { 
    	if(sets.size() == 0)
    	{
    		return sets;
    	}
    	
        Set<Set<String>> removeSet = new HashSet<Set<String>>();
        for(Set<String> s : sets)
        {
        	Set<String> s1 = new HashSet<String>();
        	if(s.contains(toRemove))
					{
						for(String value : s)
						{
							if(value != toRemove)
								s1.add(value);
						}
					}
			else
			{
				for(String value : s)
				{
					s1.add(value);
				}
			}
        	Collections.addAll(removeSet, s1);
        }
        
        return removeSet;
        				
    }
    
    
}
