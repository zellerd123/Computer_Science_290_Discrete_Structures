/**
 * An atomic proposition.
 *
 * Colgate University COSC 290L
*/

public class AtomicProp extends Prop {
    private String symbol;

    public AtomicProp(String symbol) {
        this.symbol = symbol;
    }

    public String toString() {
        return symbol;
    }

    public boolean isAtomic() {
        return true;
    }

    public boolean equals(Object other) {
        return (other instanceof AtomicProp) && symbol.equals(((AtomicProp)other).symbol);
    }
    
    public Prop getLeft(){
      return null;
    }
    
    public Prop getRight(){
      return null;
    }   

    public int hashCode() {
        return symbol.hashCode();
    }


}
