import java.io.File;
import java.util.*;

//has getRow, getVal, getCol
public class Driver{
    public tempSparseMatrix test;
    public static int count = 0;
    private tempSparseMatrix adjMatrix;  // Adjacency Matrix
    private List<List<Integer>> sccList;  // List of Strongly Connected Components (SCCs)
    private Map <Integer, Integer> sccMap = new HashMap<>();  // Map of SCCs to their indices

    public Driver(tempSparseMatrix matrix, List<List<Integer>> sccList) {
        this.adjMatrix = matrix;
        this.sccList = sccList;
        System.out.println(matrix + "~");
    }

    public void mapSCCs(){//These are our new nodes
        for (int i = 0; i < sccList.size(); i++) {
            for (int j = 0; j < sccList.get(i).size(); j++) {
                sccMap.put(sccList.get(i).get(j), i);
            }
        }
    }
}




