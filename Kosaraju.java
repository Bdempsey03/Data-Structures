import java.util.*;
public class Kosaraju {
    private int[][] adjacency;

    public Kosaraju(int[][] adjacency){
        this.adjacency = adjacency;
    }

    //Depth First search
    private void depthFirst(int value, boolean[] visited, Stack<Integer> stack){
        visited[value] = true;
        for(int i = 0; i < value; i++){
            if(adjacency[value][i] == 1 && !visited[i]){
                depthFirst(i, visited, stack);
            }
        }

        stack.push(value);

    }

    //Transpose Matrix
    private int[][] transposeMatrix(int[][] transpose){
        for(int i = 0; i < transpose.length; i++){
            for(int j = i+1; j < transpose.length; j++){
                int temp = transpose[i][j];
                transpose[i][j] = transpose[j][i];
                transpose[j][i] = temp;
            }
        }
        return transpose;
    }
    
    //Get Strongly connected components
    private void getSCC(int value, boolean[] visited, List<Integer> SCC){
        visited[value] = true;
        SCC.add(value);
        for(int i = 0; i < value; i++){
            if(!visited[i]){
                getSCC(i, visited, SCC);
            }
        }
    }
}
