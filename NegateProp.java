/**
 * A proposition of the form NOT(alpha) where alpha is proposition.
 *
 * Colgate University COSC 290L
*/

public class NegateProp extends Prop {

    private Prop left;

    public NegateProp(Prop p) {
        this.left = p;
    }

    public String getOperator() {
       return super.NEGATE_OPERATOR;
    }

    public Prop getLeft() {
        // this overrides a method in parent; see javadocs of Prop
      return left;
    }
    
    public Prop getRight() {
      // this overrides a method in parent; see javadocs of Prop
      return null;
    }
    
    public String toString() {
        // when phi is the negation of a variable, should return: "~x"
        // when phi anything else (including another negation): "~(~(p & q))"  -- include parentheses
        if (left.isAtomic())
            return super.NEGATE_OPERATOR + left;
        return super.NEGATE_OPERATOR + "(" + left + ")";
    }

    

}
