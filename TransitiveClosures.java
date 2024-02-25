import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

/**
 * A class for various static methods on relations (represented as boolean double arrays)
 *
 * Colgate University COSC 290L
 * Updated 2022
 */

public class TransitiveClosures {

    private static int simpCount;
    private static int warshCount;
    public static int SCount() {
        return simpCount;
    }
    public static int WCount() {
        return warshCount;
    }
  
    /**
     * Returns union of R and S.  Return relation T such that if (i, j) in R or (i, j) in S, then (i, j) in T.
     * @param R relation, represented as a matrix (double array)
     * @param S relation, represented as a matrix (double array)
     * @return the union of R and S
     */
    public static boolean[][] createUnion(boolean[][] R, boolean[][] S) {
        // compare matrix sizes and make sure they agree: if R is n1 x n2 then S should be n1 x n2.
        int n1 = R.length;
        int n3 = S.length;
        if (n1 == 0 || n3 == 0) {
            throw new UnsupportedOperationException("expecting non-empty arrays!");
        }
        int n2 = R[0].length;
        int n4 = S[0].length;
        if (n1 != n3 || n2 != n4) {
            throw new UnsupportedOperationException("array dimensions must match!");
        }
        boolean[][] T = new boolean[n1][n2];
        for (int i=0; i<n1; i++) {
            for (int j=0; j<n2; j++) {
                if (R[i][j] || S[i][j]) {
                    T[i][j] = true;
                }
            }
        }
        return T;
    }
    
  
    /**
     * Returns composition of R and S, denoted S ◦ R.  Returns relation T such that 
     * if (i, j) in R and (j, k) in S (i, k) in T.
     * @param S relation, represented as a matrix (double array)
     * @param R relation, represented as a matrix (double array)
     * @return the composition of R and S
     */
    public static boolean[][] createComp(boolean[][] S, boolean[][] R) {
        // compare matrix sizes and make sure they agree: if R is n1 x n2 and S is n3 x n4, then n2 = n3.
        int n1 = R.length;
        int n3 = S.length;
        if (n1 == 0 || n3 == 0) {
            throw new UnsupportedOperationException("expecting non-empty arrays!");
        }
        int n2 = R[0].length;
        int n4 = S[0].length;
        if (n2 == 0 || n4 == 0) {
            throw new UnsupportedOperationException("expecting non-empty arrays!");
        }
        if (n2 != n3) {
            throw new UnsupportedOperationException("Number of columns of R must match number of rows of S");
        }
        boolean[][]A = new boolean[n1][n4];
        for (int i=0; i<n3; i++) {
            for (int j=0; j<n4; j++) {
                if (S[i][j]) {
                    for (int k=0; k<n2; k++) {
                        if (R[k][i]) {
                            A[k][j] = true;
                        }
                        //Non-best case count:
                        simpCount++;
                    }
                }
                //Best case count:
                //simpCount++;
            }
        }
        return A;
    }

    /**
     * Returns transitive closure of R.  This method uses a less efficient algorithm than Warshall's algorithm.
     * @param R relation, represented as a matrix (double array)
     * @return the transitive closure of R
     */
    public static boolean[][] createTCSimple(boolean[][] R) {
        simpCount = 0;
        // compare matrix and make sure it's square: if R is n1 x n2 then n1 = n2
        int n = R.length;
        if (n == 0)
            throw new UnsupportedOperationException("expecting non-empty array!");
        if (R[0].length != n)
            throw new UnsupportedOperationException("expecting an n by n boolean double array!");
        boolean[][] A = R;
        for (int i=0; i<n; i++) {
            boolean[][] B = A; 
            A = createUnion(A, createComp(R,A));
            if (A == B) {
                break;
            }
        }
        return A;
    }

    
    
    /**
     * Returns transitive closure of R using Warshall's algorithm
     * does not modify argument relation R
     * @param R relation, represented as a matrix (2D boolean array, ie adjacency matrix)
     * @return the transitive closure of R
     */
    public static boolean[][] createTCWarshalls(boolean[][] R) {
        warshCount = 0;
        // compare matrix and make sure it's square: if R is n1 x n2 then n1 = n2
        int n = R.length;
        if (n == 0)
            throw new UnsupportedOperationException("expecting non-empty array!");
        if (R[0].length != n)
            throw new UnsupportedOperationException("expecting an n by n boolean double array!");

        boolean[][] A = R;
        for (int k=0; k<n; k++) {
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    if (R[i][j] || (R[i][k] && R[k][j])) {
                        A[i][j] = true;
                    }
                    warshCount++;
                }
            }
        }
        return A;
    }
    

    
    
    

//***** Below functions are useful for testing and debugging -- Please do not modify! *******




    /**
     * Returns string representation of R as a double array of T/F values.
     * @param R a relation, (2D boolean array, ie adjacency matrix)
     */
    public static String array2DToString(boolean[][] R) {
        // please do not modify
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R.length; i++) {
            for (int j = 0; j < R[i].length; j++) {
                sb.append((R[i][j]? "T":"F") + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns string representation of R as a set of pairs.
     * @param R a relation, (2D boolean array, ie adjacency matrix)
     */
    public static String relationArrToString(boolean[][] R) {
        // please do not modify
        String descriptor = "{1.." + R.length + "} x {1.." + R[0].length + "}";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R.length; i++) {
            for (int j = 0; j < R[i].length; j++) {
                String s = "(" + (i) + "," + (j) + ")";
                if (R[i][j]) {
                    sb.append("(" + (i) + "," + (j) + ")");
                    sb.append(", ");
                }
            }
        }
        return "{" + sb.substring(0, Math.max(sb.length()-2,0));
    }




}
