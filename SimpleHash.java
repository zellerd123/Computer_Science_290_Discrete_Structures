/**
 * A (very) simple hash algorithm
 *
 * Colgate University COSC 290L
 * Updated 2022
 */
public class SimpleHash implements BFHash {


    /**
     * Hashes item using the ith hash function in the hash family
     * @param item the to be hashed
     * @param i which hash function to use, allows us to generate multiple
     *        different "algorithms" from the same BFHash object
     * @return hash value
     */
    public int getHashCode(String str, int i) {
        // hashes item using its hash code and then simply adds i to the resulting value
        return (Math.abs(str.hashCode())) + i;
    }
}
