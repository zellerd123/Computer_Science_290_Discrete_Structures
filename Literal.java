
/**
 * A Literal object is a literal in a boolean proposition.  For example, in the proposition (p | ~q) & (~p | r), there are
 * four literals: p, ~p, ~q, and r.
 * 
 * Colgate University COSC 290L
 */


public class Literal {
    
    //Stores the literal and its respective negation
    private final Variable variable;
    private final boolean isNonNegated;
    
    public Literal(Variable variable, boolean isNonNegated) {
        this.variable = variable;
        this.isNonNegated = isNonNegated;
    }
    
    public Variable getVariable() {
        return variable;
    }
    
    public boolean isNonNegated() {
        return isNonNegated;
    }
    
    public boolean isNegated() {
        return !isNonNegated();
    }
    
    /**
     * Produce the literal in string form.  Use the symbol "~" to indicate a negated literal, as in ~p, for variable p.
     * @return the literal in string form
     */
    public String toString() {
        if (isNegated()) {
            return "~" + variable;
        }
        return variable.toString();
    }
    
    // ---- implementations of equals and hashcode so that this object can be put into hash sets/maps/etc.
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Literal)) {
            return false;
        }
        Literal other = (Literal) obj;
        return this.isNonNegated() == other.isNonNegated() && this.variable.equals(other.variable);
    }
    
    @Override
    public int hashCode() {
        return 31*variable.hashCode() + (isNonNegated()? 1: 0);
    }
    
}
