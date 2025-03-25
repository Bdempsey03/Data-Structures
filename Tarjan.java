
    // Java program to find strongly connected
// components in a given directed Tarjan
// using Tarjan's algorithm (single DFS)
import java.util.*;

// This class represents a directed Tarjan
// using adjacency list representation
class Tarjan {

	private int[] rowIndices;
	private int[] colPtrs;
	private int time;

	// Constructor
	public Tarjan(int[] rowIndices, int[] colPtrs) {
		this.rowIndices = rowIndices;
		this.colPtrs = colPtrs;
		this.time = 0;
	
	}
	// A recursive function that finds and prints strongly
	// connected components using DFS traversal
	// u --> The vertex to be visited next
	// disc[] --> Stores discovery times of visited vertices
	// low[] -- >> earliest visited vertex (the vertex with
	//			 minimum discovery time) that can be
	//			 reached from subtree rooted with current
	//			 vertex
	// st -- >> To store all the connected ancestors (could
	// be part
	//		 of SCC)
	// stackMember[] --> bit/index array for faster check
	//				 whether a node is in stack
	private ArrayList<SCC> SCCUtil(int u, int low[], int disc[], boolean stackMember[], Stack<Integer> st) {
		ArrayList<SCC> scc = new ArrayList<SCC>();
		// Initialize discovery time and low value
		disc[u] = time;
		low[u] = time;
		time ++;
		stackMember[u] = true;
		st.push(u);

		for(int i = colPtrs[u]; i < colPtrs[u + 1]; i++) {

			int value = rowIndices[i];

			if (disc[value] == -1) {
				scc.addAll(SCCUtil(value, low, disc, stackMember, st));

				// Check if the subtree rooted with value
				// has a connection to one of the
				// ancestors of u
				// Case 1 (per above discussion on
				// Disc and Low value)
				low[u] = Math.min(low[u], low[value]);
			}
			else if (stackMember[value] == true) {

				// Update low value of 'u' only if 'value' is
				// still in stack (i.e. it's a back edge,
				// not cross edge).
				// Case 2 (per above discussion on Disc
				// and Low value)
				low[u] = Math.min(low[u], disc[value]);
			}
		}

		// head node found, pop the stack and print an SCC
		// To store stack extracted vertices
		SCC s = new SCC(new ArrayList<Integer>());
		int w = -1;
		if (low[u] == disc[u]) {
			while (w != u) {
				w = (int)st.pop();
				s.add(w);
				// System.out.print(w + " ");

				stackMember[w] = false;
			}
			scc.add(s);
			// System.out.println();
		}
		return scc;
	}

	// The function to do DFS traversal.
	// It uses SCCUtil()
	public ArrayList<SCC> SCC()
	{
		ArrayList<SCC> scc = new ArrayList<SCC>();
		// Mark all the vertices as not visited
		// and Initialize parent and visited,
		// and ap(articulation point) arrays
		int disc[] = new int[colPtrs.length - 1];
		int low[] = new int[colPtrs.length - 1];
		for (int i = 0; i < (colPtrs.length - 1); i++) {
			disc[i] = -1;
			low[i] = -1;
		}

		boolean stackMember[] = new boolean[colPtrs.length - 1];
		Stack<Integer> st = new Stack<Integer>();

		// Call the recursive helper function
		// to find articulation points
		// in DFS tree rooted with vertex 'i'
		for (int i = 0; i < colPtrs.length - 1; i++) {
			if (disc[i] == -1)
				scc.addAll(SCCUtil(i, low, disc, stackMember, st));
		}
		return scc;
	}

	// Driver code
	public static void main(String args[])
    {
 
        // Create a Tarjan given in the above diagram
        int[] testRowIndices = {0, 1, 2, 1, 4, 1, 2, 3, 4, 3, 4};  // Edges stored in order
        int[] testColPointers = {0, 1, 3, 6, 8, 11};  // Start index of each column

        Tarjan graph = new Tarjan(testRowIndices, testColPointers);

        System.out.println("SCCs in the graph:");
        System.out.println(graph.SCC());

        int[] newArr = Evaluator.makeInvPerm(testRowIndices, testColPointers, graph.SCC());
		for(int e: newArr){
			System.out.print(e + " ");
		}
    }
}
// This code is contributed by
// Prateek Gupta (@prateekgupta10)
