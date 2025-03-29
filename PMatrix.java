//Create a virtual matrix with a getElement method that fetches element from original matrix after permutation
public class PMatrix {
	
	int[] rows;
	int[] cols;
	int[] perm;
	int size;
	
	public PMatrix (int[] rows, int[]cols, int[] perm) {
		this.rows = rows;
		this.cols = cols;
		this.perm = perm;
		this.size = perm.length;
	
	}
	
	public int getElementP(int m, int n) { //find if value at A(perm[m], perm[n]) is a 1 or 0
		
		if(m<0 || m>=size || n<0 || n>=size) {
			throw new IndexOutOfBoundsException("Indices out of bound");
		}
		
		int i = perm[m]; //map element of the virtual permutated matrix to element in original
		int j = perm[n];
		
		for(int k = cols[j]; k<cols[j+1]; k++) {
			if(rows[k] == i)
				return 1;
		}
		
		return 0;
		
	}
	
}
