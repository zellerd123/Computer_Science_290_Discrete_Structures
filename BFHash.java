/**
 * Interface for hashing algorithms to be used with the included BloomFilter class
 *
 * Colgate University COSC 290L
 * Updated 2022
 */
public interface BFHash {

    /**
     * Hashes item using the ith hash function in the hash family
     * @param item the to be hashed
     * @param i which hash function to use, allows us to generate multiple
     *        different "algorithms" from the same BFHash object
     * @return hash value
     */
    public int getHashCode(String element, int i);
    


}
