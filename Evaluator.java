
public class Evaluator {
	
    public static void evaluate(int[] rows, int[] cols) {
		int fbm = 0;	//feedback marks(#ones above the diagonal)
        int tfbd = 0;	//total feedback distance(sum of distances between ones above the diagonal and the diagonal)
        int matrixDimension = cols.length - 1;
        
        for (int j = 0; j < matrixDimension; j++) {	
            int startCol = cols[j]-1;	//where row starts corresponding to current column(-1 to adjust for 0-based indexing)
            int endCol = cols[j + 1]-1; //where row of current column ends. if last col, all remaining elements are in row of last col

            for (int k = startCol; k < endCol; k++) {
                int i = rows[k]-1; 
                if (i != j) {	//dont care about ones on diagonal
                	{
                		if (j - i > 0) { //only ones above diagonal
                            fbm++;
                            tfbd += (j - i);
                        }
              
                	}
                    
                }
            }
        }
        
        System.out.println("FBM: " + fbm);
        System.out.println("TFBD: " + tfbd);
	}
}
