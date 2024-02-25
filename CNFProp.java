import java.util.*;
import java.util.stream.Collectors;

/**
 * A CNFProp object represents a proposition in conjunctive normal form (CNF).  A CNF 
 * proposition is a conjunction of one or more clauses, where each clause a disjunction 
 * of one or more literals.
 * 
 * Colgate University COSC 290L
 */

public class CNFProp {
    
    //Collection of the proposition's clauses
    private Set<Clause> clauses = new HashSet<>();
    
    //tracks recursive calls of previous search
    //For testing -- please don't modify
    private int numRecursiveCalls = -1; 
    
    public CNFProp(Collection<Clause> clauses) {
        if (clauses.size() == 0) {
            throw new RuntimeException("Must contain at least one clause!");
        }
        this.clauses.addAll(clauses);
    }
    
    //Retrieves the clauses that construct the CNF proposition
    public Set<Clause> getClauses() {
        return clauses;
    }
    
    
    /**
     * Returns a Set that contains all of the variables that appear in any
     * clause in this proposition.
     * @return a set of Variable objects
     */
    public Set<Variable> getAllVariables() {
        // to implement this method, loop through the clauses, get their variables,
        // and add those variables to a set that you return
        Set<Variable> variables = new HashSet<Variable>();
       for (Clause c : clauses){
            Set<Literal> lits = c.getLiterals();
          for (Literal lit : lits)
             variables.add(lit.getVariable());            
       }
        return variables;
    }
    
      
    
    
    /**
     * Checks whether this proposition is satisfiable.
     * @return true if proposition is satisfiable, false otherwise
     */
    public boolean checkSatisfiabilitySE() {
       
       //****  DO NOT MODIFY THIS METHOD! ****
        numRecursiveCalls = 0;  //please leave this line
        
        Set<Variable> variables = new HashSet<>();
        variables.addAll(this.getAllVariables());
        VariableAssignment model = new VariableAssignment(variables);
        return satisfiabilitySEHelper(model);
    }
    
    
    
    //Recursive helper function for checkSatisfiabilitySE()
    //Implement me last!!
    private boolean satisfiabilitySEHelper(VariableAssignment va) {
        numRecursiveCalls++;   // please leave this line
        
        Set<Variable> vars = this.getAllVariables();                                   
        for (Variable i : vars){
            if (va.isAssigned(i))
                return solveProp(clauses);
        }
        return true;
     }
     
     // solve proposition
     private boolean solveProp(Set<Clause> s){
         for (Clause i : s){
             if (!solveClause(i))
                 return false;
         }
         return true;
     }
     
     // solve clause
     private boolean solveClause(Clause c){
         Set<Literal> lits = c.getLiterals();
         for (Literal l : lits){
             if (l.isNonNegated())
                 return true;
         }
         return false;
     }
 
    





    
    
    
    
    
    
    





    //*************************************************************
    //*                                                           *
    //*          YOU CAN IGNORE THE CODE BELOW THIS LINE          *
    //*                                                           *
    //*************************************************************



    
    
    


    

    
    /**
     * Factory method for CNFProp objects.  See its usage in the main method.
     * Builds a CNFProp by parsing the given string.  Expects proposition of the form "(p | ~q) & (p) & (~q | r)"  In other
     * words, clauses combined with & and surrounded by parenthesis.  Literals within clause are combined with | and
     * negated literals preceded by ~.
     * @param cnfStr
     * @return
     */
    public static CNFProp fromString(String cnfStr) {
        String[] clauseStrs = cnfStr.split("\\s*&\\s*");
        List<Clause> clauses = new LinkedList<>();
        for (String clauseStr : clauseStrs) {
            if (!(clauseStr.startsWith("(") & clauseStr.endsWith(")"))) {
                throw new RuntimeException("invalid clause: expected open and close parens: '" + clauseStr + "'");
            }
            clauseStr = clauseStr.substring(1, clauseStr.length() - 1);
            if (clauseStr.contains("(") || clauseStr.contains(")")) {
                throw new RuntimeException("Invalid clause: '" + clauseStr + "'.  Clauses should not contain " +
                                           "parentheses");
            }
            String[] literalStrs = clauseStr.split("\\s*\\|\\s*");
            List<Literal> literals = new LinkedList<>();
            for (String literalStr : literalStrs) {
                boolean isNegated;
                isNegated = literalStr.startsWith("~");
                String variableStr = literalStr;
                if (isNegated) {
                    variableStr = literalStr.substring(1);
                }
                Variable v = new Variable(variableStr);
                Literal literal = new Literal(v, !isNegated);
                literals.add(literal);
            }
            Clause clause = new Clause(literals);
            clauses.add(clause);
        }
        return new CNFProp(clauses);
    }
    
    
    public String toString() {
        return clauses.stream()
            .map(i -> i.toString())
            .collect(Collectors.joining(" & "));
    }
    
    
    /**
     * Returns the number of recursive calls made during the previous execution of isSatisfiable.
     * @return number of recursive calls made in execution of isSatisfiable.
     */
    public int getLastSearchCost() {
        return numRecursiveCalls;
    }    
    
}
