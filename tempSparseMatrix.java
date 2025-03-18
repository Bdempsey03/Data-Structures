import java.io.File;

public class tempSparseMatrix {
    
    int[][] matrix;

    public tempSparseMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
    public tempSparseMatrix(char I, int n){
        matrix = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i == j){
                    matrix[i][j] = 1;
                }
                else
                matrix[i][j] = 0;
            }
        }
    }
    public tempSparseMatrix(File file){//hardcoded matrix

        matrix = new int[5][5];

        matrix[0][0] = 1;
        matrix[0][1] = 1;
        matrix[0][2] = 1;
        matrix[1][1] = 1;
        matrix[1][4] = 1;
        matrix[2][1] = 1;
        matrix[2][2] = 1;
        matrix[3][3] = 1;
        matrix[3][4] = 1;
        matrix[4][3] = 1;
        matrix[4][4] = 1;

        //read the file and store the matrix in compressed sparse column format
    }
    public int getDim(){
        return matrix.length;
    }
    public int getRow(int i){
        return matrix[i].length;
    }
    public int getVal(int i, int j){
        return matrix[i][j];
    }
    public int[] getColumn(int i){
        return matrix[i];
    }
    public String toString(){
        String s = "";
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                s += matrix[i][j] + " ";
            }
            s += "\n";
        }
        return s;
    }
    public void swapColumn(int i, int j) {
        int[] temp = matrix[i];
        matrix[i] = matrix[j];
        matrix[j] = temp;
    }
    public void setVal(int i, int j, int val) {
        matrix[i][j] = val;
    }
    
    
}
