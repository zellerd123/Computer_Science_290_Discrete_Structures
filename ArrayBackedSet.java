import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * A Set that holds a set of strings, backed by a fixed-length array.
 *
 * Colgate University COSC 290L
 * 
 */

public class ArrayBackedSet {
    
    //min and max load factors, used to determine when Set needs to resize
    //(load factor = cardinality / capacity)
    private static final double MIN_LOAD_FACTOR = 0.25;
    private static final double MAX_LOAD_FACTOR = 1.0;    
    
    private static final int MIN_CAPACITY = 2;
    
    private String[] elements;             // where elements should be stored (order does not matter)
    private int cardinality;               // the number of elements in the set
    
    /**
     * Create a set via enumeration.  Each element in elementsToAdd should be added to the set.  Note that
     * elementsToAdd may contain duplicates but the created set should not have any duplicates.
     * a null argument is treated like an empty array
     * @param elementsToAdd elements to add to the set
     */
    public ArrayBackedSet(String[] elementsToAdd) {
        // initialize the set by adding all of the elementsToAdd to it
        elements = new String[elementsToAdd.length];
        for (int i = 0; i < elementsToAdd.length; i++){
            insertElement(elementsToAdd[i]);
        }
    }
    
    /**
     * Create an empty set.
     */
    public ArrayBackedSet() {        
        //Don't forget about constructor redirection using 'this'
        //How can you eliminate redundancy here?
       this(new String[MIN_CAPACITY]);
        
    }
    
    /**
     * Check for membership in set.
     * @param element element to be checked
     * @return true if element is in the set, false otherwise
     */
    public boolean containsElement(String element) {
        for (int i = 0; i < cardinality; i++){
            if (elements[i].equals(element)){
                return true; 
            }
        }
        return false; 
    }
    
    /**
     * Adds one element to set, modifying the set.
     * @param element the element to add
     * @return true if element is succesfully added, false otherwise
     */
    public boolean insertElement(String element) {
        if (element == null || element.length() < 1 || containsElement(element)){
            return false;
        }
        incSize();
        elements[cardinality] = element;
        cardinality++;
        return true;
    }
    
public void incSize() {
    if(cardinality/elements.length >= MAX_LOAD_FACTOR){
        String [] DOUB_elements = new String[elements.length*2];
        for (int i = 0; i < elements.length; i++) {
            DOUB_elements[i] = elements[i];
        }
        elements = DOUB_elements;
    }
}

    /**
     * Removes one element from the set, modifying the set.
     * @param element the element to remove
     * @return true if the element is succesfully removed, false otherwise
     */
    public boolean removeElement(String element) {
        if (element == null || element.length() < 1){
            return false;
        }
        for (int i = 0; i < elements.length; i++){
            if (elements[i] == element){
                for (int j = i; j < elements.length-1; j++){
                    elements[j] = elements[j+1];
                }
                 elements[cardinality-1] = null;
                if (cardinality > 0){
                    cardinality--;
                }
                lowerSize();
                return true;
            }
            
        }
        return false;
    }
   
    
    public void lowerSize(){
        double amount = (float)cardinality/(float)elements.length;
        System.out.println(this.toString());
        if (amount <= MIN_LOAD_FACTOR){
            if(elements.length <= MIN_CAPACITY) {
                return;
            }
            else{
                String [] DIV_elements = new String[elements.length/2];
                for (int i = 0; i < cardinality; i++) {
                    DIV_elements[i] = elements[i];
                }
                elements = DIV_elements;
            }
           
            
        }
        return;
    }
    

    /**
     * Size (aka cardinality) of the set.
     * @return the size of the set (number of distinct elements)
     */
    public int getCardinality() {
        return cardinality;
    }
    
    /**
     * Capacity of the set.
     * @return the current storage consumed by this implementation
     */
    public int getCapacity() {
       return elements.length;
    }    
    
    
    /**
     * Checks of two sets are equal.
     * Sets are equal if they contain the same elements (which do
     * not necessarily need to be in the same order).  Likewise, two
     * equal sets CAN have different capacities; all that matters is the elements.
     * @return if this ArrayBackedSet is equal to the argument object
     */    
   public boolean equals(Object other){
        //Be mindful of the argument type here -- you cannot change it!
        boolean isEqual = false;
        ArrayBackedSet truck = (ArrayBackedSet)other;
        if (other instanceof ArrayBackedSet){
            if(cardinality == truck.getCardinality()){
                if (cardinality == 0){
                    return true;
                }
                for (int i = 0; i < cardinality; i++){
                    if(truck.containsElement(elements[i]) == true)
                    {
                        isEqual = true;
                    }
                    else
                        return false;
                }
            }
            
        }
        
        
        return isEqual;
    }
    
    /**
     * Returns all elements of the ArrayBackedSet in an appropriately sized
     * String array (ie, contains no extra null blank spots).
     * Note that since an ArrayBackedSet is unordered, the elements in the 
     * returned array won't be in any particular order.
     * @return a String[] containing all elements in this Arrayset
     */    
    public String[] toArray(){
        String[] retString = new String[cardinality];
        for (int i = 0; i < cardinality; i++){
            retString[i] = elements[i];
        }
        return retString;
    }
    
    
    /**
     * Returns a String representation of elements in the ArrayBackedSet
     * @return String of all elements in the ArrayBackedSet
     */
    public String toString(){
        if (cardinality == 0)
            return "{}";
        String toReturn = "";
        for (int i  = 0; i < cardinality; i++){
            toReturn += ", " + elements[i];
        }
        return "{" + toReturn.substring(2) + "}";
    }
    
    
}
