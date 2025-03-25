import java.util.ArrayList;

public class Evaluator {
    private int[] invPerm;

    public Evaluator(SparseMatrix matrix, ArrayList<SCC> SCCs) {
        makeInvPerm(matrix, SCCs);
    }   

	
    public void evaluate(int[] rows, int[] cols) {
		int fbm = 0;	//feedback marks(#ones above the diagonal)
        int tfbd = 0;	//total feedback distance(sum of distances between ones above the diagonal and the diagonal)
        int matrixDimension = cols.length - 1;
        
        for (int j = 0; j < matrixDimension; j++) {	
            int startCol = cols[invPerm(j)]-1;	//where row starts corresponding to current column(-1 to adjust for 0-based indexing)
            int endCol = cols[invPerm(j + 1)]-1; //where row of current column ends. if last col, all remaining elements are in row of last col

            for (int k = startCol; k < endCol; k++) {
                int i = rows[invPerm(k)]-1; 
                if (invPerm(i) != invPerm(j)) {	//dont care about ones on diagonal
                	{
                		if (invPerm(j) - invPerm(i) > 0) { //only ones above diagonal
                            fbm++;
                            tfbd += (invPerm(j) - invPerm(i));
                        }
              
                	}
                    
                }
            }
        }
        
        System.out.println("FBM: " + fbm);
        System.out.println("TFBD: " + tfbd);
	}
    public void makeInvPerm(SparseMatrix original, ArrayList<SCC> SCCs){
        int[] perm = new int[original.getCols().length-1];
        int[] invPerm = new int[original.getCols().length-1];
        for(SCC scc : SCCs){
            for(int i = 0; i < scc.nodes.size(); i++){
                perm[i] = scc.nodes.get(i);
            }
        }
        for(int i = 0; i < perm.length; i++){
            invPerm[perm[i]] = i;
        }
        this.invPerm = invPerm;
    }

    private int invPerm(int i){
        return invPerm[i];
    }
}
