import java.util.*;
public class Kosaraju {
    private int[][] adjacency;

    public Kosaraju(int[][] adjacency){
        this.adjacency = adjacency;
    }

    //Depth First search
    private void depthFirst(int value, boolean[] visited, Stack<Integer> stack){
        visited[value] = true;
        for(int i = 0; i < adjacency.length; i++){
            if(adjacency[value][i] == 1 && !visited[i]){
                depthFirst(i, visited, stack);
            }
        }

        stack.push(value);

    }

    //Transpose Matrix
    private Kosaraju transposeMatrix(int[][] base){
        int[][] transpose = new int[base.length][base.length];
        for(int i = 0; i < adjacency.length; i++){
            for(int j = i+1; j < adjacency.length; j++){
                transpose[i][j] = base[j][i];
            }
        }

        Kosaraju transposedVer = new Kosaraju(transpose);
        return transposedVer;
    }
    
    //Get Strongly connected components
    private void getSCC(int value, boolean[] visited, List<Integer> SCC){
        visited[value] = true;
        SCC.add(value);
        for(int i = 0; i < adjacency.length; i++){
            if(adjacency[value][i] == 1 && !visited[i]){
                getSCC(i, visited, SCC);
            }
        }
    }

    public List<List<Integer>> runAlgorithm(){
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[adjacency.length];

        for(int i = 0; i < adjacency.length; i++){
            if(!visited[i]){
                depthFirst(i, visited, stack);
            }
        }

        Kosaraju transpose = transposeMatrix(adjacency);

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
        int[][] test = {
            {0, 1, 0, 0, 0}, 
            {0, 0, 1, 0, 0}, 
            {0, 0, 0, 1, 0}, 
            {1, 0, 0, 0, 0}, 
            {0, 0, 0, 1, 0}
        };
        Kosaraju testImplement = new Kosaraju(test);
        List<List<Integer>> stronglyConnectedComponents = testImplement.runAlgorithm();
        System.out.println("SCCs: ");
        for(List<Integer>scc : stronglyConnectedComponents){
            System.out.println(scc);
        }
    }
}
