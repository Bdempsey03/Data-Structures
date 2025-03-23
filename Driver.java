import java.io.File;
import java.util.*;

//has getRow, getVal, getCol
public class Driver{
    public tempSparseMatrix test;
    public static int count = 0;
    private tempSparseMatrix adjMatrix;  // Adjacency Matrix
    private tempSparseMatrix condensedAdjMatrix;  // Condensed Adjacency Matrix
    private List<List<Integer>> sccList;  // List of Strongly Connected Components (SCCs)
    private Map <Integer, SCC> sccMap = new HashMap<>();  // Map of SCCs to their indices

    int[][] matrix; //temp

    private SparseMatrix input;

    private List<Integer> order = new ArrayList<>();


    public Driver(tempSparseMatrix matrix, List<List<Integer>> sccList) throws Exception {
        input = new SparseMatrix(new File("./Data/GD98_a/GD98_a.mtx"));
        //System.out.println(input);
        this.adjMatrix = matrix;
        this.sccList = sccList;
        //System.out.println(matrix + "~"); 
    }



    public void mapSCCs(){//These are our new nodes
        SCC scc;
        for (int i = 0; i < sccList.size(); i++) {
            scc = new SCC(new ArrayList<Integer>());
            for (int j = 0; j < sccList.get(i).size(); j++) {
                scc.add(sccList.get(i).get(j));
            }
            sccMap.put(scc.getFirst(), scc);
        }
        //System.out.println(sccMap + "Map!");
    }

    public void buildCondensedMatrix(){ //need matrix constructor that takes a matrix and an scc and makes a new matrix
        matrix = new int[4][4];
        
        matrix[1][0] = 1;
        matrix[1][2] = 1;
        matrix[2][0] = 1;
        matrix[3][1] = 1;
        condensedAdjMatrix = new tempSparseMatrix(matrix);

        // for(int i = 0; i < matrix.length; i++){
        //     for(int j = 0; j < matrix[i].length; j++){
        //         //System.out.print(matrix[i][j] + " ");
        //     }
        //     //System.out.println();
        // }

        // Make a new matrix with the SCCs as nodes and no diagonal elements
    }
    public void computeTopologicalOrder(){ //col[5]-col[4] = inDegree of col[4]
        //System.out.println(condensedAdjMatrix + "condensed");
        int n = condensedAdjMatrix.getDim();
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inDegree[j] += condensedAdjMatrix.getVal(i, j); //if there is an edge in j then we increase the inDegree of j
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
            //System.out.println(queue + "queue");

        }
        

        // Save the order

        while(!queue.isEmpty()){//Add the node with the least inDegree to the order (most to the left in the output)
            int node = queue.poll();
            order.add(node);

            //Now we decrement every inDegree and add all that have degree 0, and then repeat until we have added all nodes
            for (int i = 0; i < n; i++) {
                if (condensedAdjMatrix.getVal(node, i) == 1) {
                    inDegree[i]--;
                    if (inDegree[i] == 0) {
                        queue.add(i);
                    }
                }
            }
        }
        for(int i = 0; i < order.size(); i++){
            //System.out.print(sccMap.get(order.getFirst()) + " ");
        }//System.out.println("order");


    }

    public void constructP(){
        int[] ordering = topologicalSort(matrix);
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < ordering.length; i++){

            //System.out.println(sccMap.get(ordering[i]));
            for(int j = 0; j < sccMap.get(ordering[i]).size(); j++){
                queue.add(sccMap.get(ordering[i]).get(j));
            }
        }
        ArrayList<Integer> ANSWER = new ArrayList<>();
        while(!queue.isEmpty()){
            //System.out.println(queue.peek());
            ANSWER.add(queue.poll());
        }
        System.out.println(ANSWER + "ANSWER");
        System.out.println("^^");
        //permute
    }

    /**
     * Performs the O(v^2) topological sort algorithm on the given adjacency matrix
     * @param adj The adjacency matrix of the graph
     */
    public static int[] topologicalSort(int[][] adj) {

        // Setup
        int n = adj.length;
        boolean[] visited = new boolean[n];
        int[] order = new int[n];
        int index = n - 1;
    
        // Visit each node
        for (int u = 0; u < n; u++){ 
          if (!visited[u]){
            index = visit(adj, visited, order, index, u);
          }
        }
        // Return topological sort
        return order;
      }
    
      private static int visit(int[][] adj, boolean[] visited, int[] order, int index, int u) {
    
        if (visited[u]) return index;
        visited[u] = true;
    
        // Visit all adjacent
        for (int v = 0; v < adj.length; v++)
          if (adj[u][v] != 0 && !visited[v]) { 
            index = visit(adj, visited, order, index, v);
          }
    
        // Place this node at the head of the list
        order[index--] = u;
      
    
        return index;
      }

}




