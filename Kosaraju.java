import java.util.*;
public class Kosaraju {
    private int[] rowIndices;
    private int[] colPtrs;

    public Kosaraju(int[] rowIndices, int[] colPtrs) {
        if (colPtrs.length < 2) {
            throw new IllegalArgumentException("colPtrs must have at least two elements.");
        }
        if (rowIndices.length < colPtrs[colPtrs.length - 1]) {
            throw new IllegalArgumentException("rowIndices length must match the last value in colPtrs.");
        }
        this.rowIndices = rowIndices;
        this.colPtrs = colPtrs;
    }

    //Depth First search
    private void depthFirst(int value, boolean[] visited, Stack<Integer> stack) {
        if (value < 0 || value >= colPtrs.length - 1) {
            throw new IndexOutOfBoundsException("Invalid value index: " + value);
        }
        visited[value] = true;
        for (int i = colPtrs[value]; i < colPtrs[value + 1]; i++) {
            if (i < 0 || i >= rowIndices.length) {
                throw new IndexOutOfBoundsException("Invalid index in rowIndices: " + i);
            }
            int neighbor = rowIndices[i];
            if (neighbor < 0 || neighbor >= visited.length) {
                throw new IndexOutOfBoundsException("Invalid neighbor index: " + neighbor + ", visited.length: " + visited.length);
            }
            if (!visited[neighbor]) {
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
    private void getSCC(int value, boolean[] visited, SCC scc){
        visited[value] = true;
        scc.add(value);
        for(int i = colPtrs[value]; i < colPtrs[value + 1]; i++){
            int neighbor = rowIndices[i];
            if(!visited[neighbor]){
                getSCC(neighbor, visited, scc);
            }
        }
    }

    public ArrayList<SCC> runAlgorithm(){
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[colPtrs.length - 1];

        for(int i = 0; i < (colPtrs.length - 1); i++){
            if(!visited[i]){
                depthFirst(i, visited, stack);
            }
        }

        Kosaraju transpose = transposeMatrix();

        Arrays.fill(visited, false);

        ArrayList<SCC> stronglyConnectedComponents = new ArrayList<>();

        while(!stack.isEmpty()){
            int value = stack.pop();
            if(!visited[value]){
                ArrayList<Integer> componentNodes = new ArrayList<>();
                SCC scc = new SCC(componentNodes);
                transpose.getSCC(value, visited, scc);
                stronglyConnectedComponents.add(scc);
            }
        }
        stronglyConnectedComponents.remove(stronglyConnectedComponents.size()-1);
        return stronglyConnectedComponents;
    }

    // public static void main(String[] args){
    //     int[] testRowIndices = {0, 1, 2, 1, 4, 1, 2, 3, 4, 3, 4};  // Edges stored in order
    //     int[] testColPointers = {0, 1, 3, 6, 8, 11};  // Start index of each column
    //     Kosaraju testImplement = new Kosaraju(testRowIndices, testColPointers);
    //     ArrayList<SCC> stronglyConnectedComponents = testImplement.runAlgorithm();
    //     System.out.println("SCCs: ");
    //     for(SCC scc : stronglyConnectedComponents){
    //         System.out.println(scc);
    //     }
    // }
}
