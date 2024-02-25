import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * A smarter (than SimpleHash) hash algorithm
 *
 * Colgate University COSC 290L
 * Updated 2022
 */
public class AdvancedHash implements BFHash {
    



    /**
     * Hashes item using the ith hash function in the hash family
     * @param item the to be hashed
     * @param i which hash function to use, allows us to generate multiple
     *        different "algorithms" from the same BFHash object
     * @return hash value
     */
    public int getHashCode(String str, int i) {
        int hash = str.hashCode();
        // divide hash into two numbers by keeping every other bit
        int h1 = hash & 0x5555555;  // 0101 = 5 in hexadecimal
        int h2 = hash & 0xaaaaaaa;  // 1010 = 10 = "a" in hexadecimal
        // use two hashes to make family of k hashes:
        // see Kirsch & Mitzenmacher, "Less Hashing, Same Performance: Building a Better Bloom Filter"
        int result = (h1 + (i * h2));
        return result;
    }
}
