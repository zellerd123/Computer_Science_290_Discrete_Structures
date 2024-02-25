import java.util.Arrays;

/**
 * A proposition of the form alpha OP beta where alpha and beta are propositions and OP is a logical connective (AND,
 * OR, IMPLIES, etc).
 *
 * Colgate University COSC 290L
*/
public class BinaryProp extends Prop {

    private String op;
    private Prop left;
    private Prop right;


    public BinaryProp(String op, Prop p, Prop q) {
        if (Arrays.asList(Prop.ALL_OPERATORS).indexOf(op) < 0 || op.equals(Prop.NEGATE_OPERATOR))
          throw new IllegalArgumentException("'" + op + "' is not a valid operator!");
        this.op = op;
        this.left = p;
        this.right = q;
    }

    public String getOperator() {
        return op;
    }

    public Prop getLeft() {
        return left;
    }

    public Prop getRight() {
        return right;
    }

    public String toString() {
        return getSubstring(left) + " " + op.toString() + " " + getSubstring(right);
    }

    
    // be clever about parentheses: ((p & q) & r) will be written simply as (p & q & r)
    private String getSubstring(Prop q) {
        String pStr = "";
        if (q.isBinary()) {
            if(q.isImpl())
                pStr = "(" + q.toString() + ")";
            else if (((BinaryProp)q).op.equals(this.op)) 
                pStr = q.toString();
            else 
                pStr = "(" + q.toString() + ")";
        } 
        else 
            pStr = q.toString();
        return pStr;
    }
    

}
