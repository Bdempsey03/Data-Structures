import java.io.File;

public class Main{



    private static SparseMatrix input; //has getRow, getVal, getCol
    private static int[][] tarjanOutput; //output of Tarjan algorithm


    private void permuteMatrix(String file){

        //permute the adjacency matrix while maintaining the order of the strongly connected components
        input = new SparseMatrix(new File(file));
        
    }


    public static void main(String[] args) {
        
        
        
        //Take the output of the Tarjan algorithm and permute the adjacency matrix while maintaining the order of the strongly connected components
        
        

    
    }
}