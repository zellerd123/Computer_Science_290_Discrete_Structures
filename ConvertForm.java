/**
 * Static methods to help write complex propositions, as well as
 * convert propsition between forms
 *
 * Colgate University COSC 290L
 */

public class ConvertForm {
    

    
    
    
    /**
     * Constructs a proposition psi such that (1) psi is in simplified form, meaning it contains only 
     * the logical connectives in {&, ~} and (2) psi is logically equivalent to phi.
     * @param phi a proposition to simplify
     * @return psi, a proposition with only & and ~ connectives and logically equivalent to phi
     */
    public static Prop toSimplified(Prop phi) {
        if (phi instanceof AtomicProp) 
            return phi; 
        else if (phi instanceof NegateProp) 
            return new NegateProp(toSimplified(phi.getLeft()));         
        Prop newLeft = toSimplified(phi.getLeft()); 
        Prop newRight = toSimplified(phi.getRight());         
        if (phi.isDisj()){ 
            Prop temp =  Prop.makeConj(Prop.makeNegate(newLeft), Prop.makeNegate(newRight)); 
            return new NegateProp(temp); 
        } 
        else if (phi.isImpl()){ 
            Prop temp =  Prop.makeConj(newLeft, Prop.makeNegate(newRight)); 
            return new NegateProp(temp); 
        } 
        return Prop.makeConj(newLeft, newRight);
    }
    
    
    /**
     * Constructs a proposition psi such that (1) psi is in negation normal form  and (2) psi is logically
     * equivalent to phi.  Expects a proposition that has only & and ~ connectives.
     *
     * A sentence is in negation normal form if the negation connective is applied only to atomic propositions (i.e.
     * variables) and not to more complex expressions, and furthermore, the only connectives allowed are {&, |, ~}.
     *
     * @param phi a proposition in simplified form to transform to NNF
     * @return psi, a proposition in NNF and logically equivalent to phi
     * @throws IllegalArgumentException if phi is not in simplified form.
     */
    public static Prop simplifiedToNNF(Prop phi) {
        if (phi instanceof AtomicProp) 
            return phi; 
        else if (phi instanceof NegateProp){ 
            Prop negProp = phi.getLeft();        
            if (negProp instanceof AtomicProp) 
                return new NegateProp(negProp); 
            else if (negProp.isConj()) 
                return Prop.makeDisj(simplifiedToNNF(Prop.makeNegate(negProp.getLeft())), simplifiedToNNF(Prop.makeNegate(negProp.getRight()))); 
            else if (negProp instanceof NegateProp) 
                return simplifiedToNNF(negProp.getLeft()); 
        } 
        else if (phi.isConj()){ 
            Prop newLeft = simplifiedToNNF(phi.getLeft()); 
            Prop newRight = simplifiedToNNF(phi.getRight()); 
            return Prop.makeConj(newLeft, newRight); 
        } 
        throw new IllegalArgumentException("Prop contains invalid operators!: " + phi);
    }
    
    
    
    /**
     * Constructs a proposition psi such that (1) psi is in CNF and (2) psi is logically
     * equivalent to phi.  Expects a proposition phi that is in NNF.
     *
     * @param phi a proposition in NNF to transform to CNF
     * @return psi, a proposition in CNF and logically equivalent to phi
     * @throws IllegalArgumentException if phi is not in NNF
     */
    public static Prop NNFToCNF(Prop phi) {
        //In order to be in CNF, we can't have any "ands" that are descendents of "ors"!!
        if (phi.isAtomic() || (phi.isNegate() && phi.getLeft().isAtomic()))
            return phi;
        else if (!phi.isBinary() || phi.isImpl()) //if true, phi must not be in NNF
            throw new IllegalArgumentException("Prop contains invalid operators!");

        //convert sub trees to CNF, this ensures any "and"'s have been bubbled to the top of both subtrees
        Prop alpha = NNFToCNF(phi.getLeft());
        Prop beta = NNFToCNF(phi.getRight());
        //(hint, treat the recursive call like a "black box" -- assume alpha and beta are now in CNF)
        if (phi.isConj()) //if conj, nothing else to be done now that children are CNF -- we're good!
            return Prop.makeConj(alpha, beta);

        //otherwise, phi must be an "or", thus we may need to "bubble" this "or"
        //down w/ distribution law if any of its children are "and"s
        return NNFDisjToCNF(Prop.makeDisj(alpha, beta));
    }




    /**
     * Recursive helper for NNFToCNF -- converts the argument tree to CNF
     * equivalent. Argument phi is partially in CNF (see below)
     *
     * Given how this functions is utilzed in NNFToCNF, what assumptions
     * can we make about phi?
     *
     *
     * @param phi a proposition to transform to CNF that meets the assumptions above
     * @return psi, a proposition in CNF and logically equivalent to phi
     */
    private static Prop NNFDisjToCNF(Prop phi){
      //hint - given the assumptions about phi,
      //if phi's left and right children have any ANDs, where will they be?
      
      //furthermore, what will your base case(s) be here?
      //given the assumptions about phi's children, when do you know phi is CNF?
      Prop alpha = phi.getLeft();
      Prop beta = phi.getRight();  
      if (!alpha.isConj()&& !beta.isConj()) return phi;
            
      else if (alpha.isConj()){
        Prop lAlpha = alpha.getLeft();
        Prop rAlpha = alpha.getRight(); 
        return Prop.makeConj(NNFDisjToCNF(Prop.makeDisj(lAlpha, beta)),NNFDisjToCNF(Prop.makeDisj(rAlpha, beta)));
        
      }
      else {
        Prop lBeta = beta.getLeft();
        Prop rBeta = beta.getRight(); 
        return Prop.makeConj(NNFDisjToCNF(Prop.makeDisj(alpha, lBeta)),NNFDisjToCNF(Prop.makeDisj(alpha, rBeta)));
        

      }
    }



    /**
     * Constructs a proposition psi such that (1) psi is conjunctive normal form and (2) psi is
     * logically equivalent to phi.
     * @param phi a proposition in any form to transform to CNF
     * @return psi, a proposition in CNF that is logically equivalent to phi
     */
    public static Prop toCNF(Prop phi) {
       
      return NNFToCNF(simplifiedToNNF(toSimplified(phi)));
    }        
    
    
}
