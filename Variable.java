/**
 * A Variable object is an atomic proposition, represented by a symbol such as "p" or "q."
 * 
 * Colgate University COSC 290L
 */

public class Variable {
    private String symbol;
    
    public Variable(String symbol) {
        this.symbol = symbol;
    }
    
    
    public String toString() {
        return symbol;
    }
    
    // ---- implementations of equals and hashcode so that this object can be put into hash sets/maps/etc.
    
    
    public boolean equals(Object other) {
        return (other instanceof Variable) && symbol.equals(((Variable)other).symbol);
    }
    
    
    public int hashCode() {
        return symbol.hashCode();
    }
}
