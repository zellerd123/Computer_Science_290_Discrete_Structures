import java.util.*;

/**
 * A VariableAssignment represents a (partial) assignment of truth values to Variables.
 * 
 * Colgate University COSC 290L
 */


public class VariableAssignment {
    
    private final Map<Variable, Boolean> assignments;
    
    /**
     * Construct a model for the given collection of variables.
     * @param variables to include in the model
     */
    public VariableAssignment(Set<Variable> variables) {
       assignments = new HashMap<Variable, Boolean>();
       for (Variable v : variables)
          assignments.put(v, null);
    }
    
    /**
     * Assign a truth value to a given variable.
     * @param v the variable
     * @param value the truth value to assign to the variable
     */
    public void setAssignment(Variable v, Boolean value) {
        if (!assignments.containsKey(v)) {
            throw new RuntimeException("Variable " + v + " is not part of this assignment!");
        }
        if (isAssigned(v)) {
            throw new RuntimeException("Variable " + v + " already assigned!");
        }
        assignments.put(v, value);
    }
    
    /**
     * Unassign a truth value to a given variable.  Expects this variable to already have been assigned.
     * @param v the variable
     */
    public void clearAssignment(Variable v) {
        if (!isAssigned(v)) {
            throw new RuntimeException("Variable " + v + " is not assigned!");
        }
        assignments.put(v, null);
    }
    
    
   /**
    * Checks if the argument variable is assigned in the model (true) or not (false)
    * Throws a RuntimeException if the argument variable is not part
    * of the VariableAssignment.
    * @param v the variable
    */
    public boolean isAssigned(Variable v) {
       if (!assignments.containsKey(v))
          throw new RuntimeException("Variable " + v + " is not part of this Assignment!");
       return (assignments.containsKey(v) && assignments.get(v) != null);
    }        
    
    
    //Retrieves the argument variable's truth value in the model
    public boolean getAssignment(Variable v) {
        if (!isAssigned(v)) {
            throw new RuntimeException("Variable " + v + " is unassigned!");
        }
        return assignments.get(v);
    }
    
    
    @Override
    public String toString() {
        return assignments.toString();
    }
    
}
