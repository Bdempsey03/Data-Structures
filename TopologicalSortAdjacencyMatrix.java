public class TopologicalSortAdjacencyMatrix {

  // Performs the topological sort and returns the
  // indexes of the nodes in topological order
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
