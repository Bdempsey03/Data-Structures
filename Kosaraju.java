import java.util.*;
public class Kosaraju {
    private int[] rowIndices;
    private int[] colPtrs;

    public Kosaraju(int[] rowIndices, int[] colPtrs){
        this.rowIndices = rowIndices;
        this.colPtrs = colPtrs;
    }

    //Depth First search
    private void depthFirst(int value, boolean[] visited, Stack<Integer> stack){
        visited[value] = true;
        for(int i = colPtrs[value]; i < colPtrs[value + 1]; i++){
            int neighbor = rowIndices[i];
            if(!visited[neighbor]){
                depthFirst(neighbor, visited, stack);
            }
        }

        stack.push(value);

    }

    //Transpose Matrix
    private Kosaraju transposeMatrix(){
        int[] transposedRow = new int[rowIndices.length];
        int[] transposedColPtrs = new int[(colPtrs.length - 1) + 1];
        //Count occurneces of row indices
        for(int i : rowIndices){
            transposedColPtrs[i + 1]++;
        }
        //Compute colPtr offsets
        for(int i = 1; i <= (colPtrs.length - 1); i++){
            transposedColPtrs[i] += transposedColPtrs[i - 1];
        }
        //Fill row indices for tranposed matrix
        int[] position = Arrays.copyOf(transposedColPtrs, transposedColPtrs.length);
        for(int column = 0; column < (colPtrs.length - 1); column++){
            for(int j = colPtrs[column]; j < colPtrs[column + 1]; j++){
                int row = rowIndices[j];
                transposedRow[position[row]++] = column;
            }
        }
        //return transposed version of CSC matrix
        Kosaraju transposedVer = new Kosaraju(transposedRow, transposedColPtrs);
        return transposedVer;
    }
    
    //Get Strongly connected components
    private void getSCC(int value, boolean[] visited, List<Integer> SCC){
        visited[value] = true;
        SCC.add(value);
        for(int i = colPtrs[value]; i < colPtrs[value + 1]; i++){
            int neighbor = rowIndices[i];
            if(!visited[neighbor]){
                getSCC(neighbor, visited, SCC);
            }
        }
    }

    public List<List<Integer>> runAlgorithm(){
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[colPtrs.length - 1];

        for(int i = 0; i < (colPtrs.length - 1); i++){
            if(!visited[i]){
                depthFirst(i, visited, stack);
            }
        }

        Kosaraju transpose = transposeMatrix();

        Arrays.fill(visited, false);

        List<List<Integer>> stronglyConnectedComponents = new ArrayList<>();

        while(!stack.isEmpty()){
            int value = stack.pop();
            if(!visited[value]){
                List<Integer> scc = new ArrayList<>();
                transpose.getSCC(value, visited, scc);
                stronglyConnectedComponents.add(scc);
            }
        }
        return stronglyConnectedComponents;
    }

    public static void main(String[] args){
        int[] testRowIndices = {1, 2, 0, 3, 4, 3};  // Outgoing edges (row indices)
        int[] testColPointers = {0, 1, 2, 4, 5, 6}; // Column start positions
        Kosaraju testImplement = new Kosaraju(testRowIndices, testColPointers);
        List<List<Integer>> stronglyConnectedComponents = testImplement.runAlgorithm();
        System.out.println("SCCs: ");
        for(List<Integer>scc : stronglyConnectedComponents){
            System.out.println(scc);
        }
    }
}
