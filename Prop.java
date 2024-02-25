/**
 * An abstract class representing a logical proposition.
 *
 * Colgate University COSC 290L
*/


public abstract class Prop {
  
    //Strings represnting the various operators
    public static final String NEGATE_OPERATOR = "~";
    public static final String CONJ_OPERATOR = "&";
    public static final String DISJ_OPERATOR = "|";
    public static final String IMPL_OPERATOR = "->";
    
    //collection of all possible operators
    protected static final String[] ALL_OPERATORS = {NEGATE_OPERATOR, CONJ_OPERATOR, DISJ_OPERATOR, IMPL_OPERATOR};

    
    /**
     * Returns a string representation of a proposition.  The string representation should include parentheses to
     * indicate the operator precedence.  Example:
     * (p & q) | r
     * @return a string representation of proposition
     */
    public abstract String toString();


    
    

    // --- HELPER METHODS FOR CHECKING THE STRUCTURE OF A COMPLEX PROPOSITION ---

    /**
     * @return true is the proposition is an atomic proposition (ex: the variable p)
     */
    public boolean isAtomic() {
        return false;
    }

    /**
     * @return true is the proposition is of the form ~phi for some proposition phi.
     */
    public boolean isNegate() {
      return NEGATE_OPERATOR.equals(this.getOperator());
    }

    /**
     * @return true is the proposition is binary -- i.e., it is of the form phi1 OPERATOR phi2 where OPERATOR is
     * some logical connective.
     */
    public boolean isBinary() {
        return isDisj() || isConj() || isImpl();
    }

    /**
     * @return true is the proposition is of the form phi1 | phi2.
     */
    public boolean isDisj() {
        return DISJ_OPERATOR.equals(this.getOperator());
    }

    /**
     * @return true is the proposition is of the form phi1 & phi2.
     */
    public boolean isConj() {
        return CONJ_OPERATOR.equals(this.getOperator());
    }

    /**
     * @return true is the proposition is of the form phi1 => phi2.
     */
    public boolean isImpl() {
        return IMPL_OPERATOR.equals(this.getOperator());        
    }

    
    /**
     * If the proposition is binary -- i.e., it is of the form  phi1 OPERATOR phi2 where OPERATOR is some logical
     * connective, the respective operator is returned (see finals above).
     * 
     * Example: if proposition is p & q, it returns &.
     * 
     * Same logic applies if the propsition is unary -- i.e., OPERATOR phi1
     *
     * Example: if proposition is ~p, it returns ~.
     * 
     * If the proposition has no operator, null is returned
     *
     * @return the logical connective associated with this sentence if it has one, otherwise null
     */
    public String getOperator() {
        return null;
    }

    /**
     * If the proposition is binary -- i.e., it is of the form phi1 OPERATOR phi2 -- then phi1 is returned.
     *
     * Example: if proposition is (p | r) & q, it returns (p | r).
     *
     * If the proposition is unary -- i.e., it is of the form OPERATOR phi1 where OPERATOR is some logical
     * connective -- then phi1 is returned.
     *
     * Example: if proposition is ~p, it returns p.
     * 
     * If there is no operator, then null is returned
     */
    public abstract Prop getLeft();

    
    
    /**
     * If the proposition is binary -- i.e., it is of the form phi1 OPERATOR phi2 where OPERATOR is some logical
     * connective -- then phi2 is returned.
     *
     * If the proposition is unary or there is no operator, then null is returned.
     *
     * @return the right argument if this sentence is a binary proposition, otherwise null
     */
    public abstract Prop getRight();


    
    
    
    
    /**
     * Returns proposition that is negation of p
     * @param p
     * @return proposition that is ~p
     */
    public static Prop makeNegate(Prop p) {
        return new NegateProp(p);
    }
    
    /**
     * Returns proposition that is conjunction of p and q
     * @param p
     * @param q
     * @return proposition that is p & q
     */
    public static Prop makeConj(Prop p, Prop q) {
        return new BinaryProp(Prop.CONJ_OPERATOR, p, q);
    }
    
    /**
     * Returns proposition that is disjunction of p and q
     * @param p
     * @param q
     * @return proposition that is p | q
     */
    public static Prop makeDisj(Prop p, Prop q) {
        return new BinaryProp(Prop.DISJ_OPERATOR, p, q);
    }
    
    /**
     * Returns proposition that is p implies q
     * @param p
     * @param q
     * @return proposition that is p => q
     */
    public static Prop makeImpl(Prop p, Prop q) {
        // look at the other examples above for tips on how to write this
        return new BinaryProp(Prop.IMPL_OPERATOR, p, q);
    }    

}
