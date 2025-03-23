import java.io.File;
import java.util.List;

public class Main{



    private static tempSparseMatrix input; //has getRow, getVal, getCol
    private static int[][] tarjanOutput; //output of Tarjan algorithm
    private tempSparseMatrix best;
    private tempSparseMatrix p = new tempSparseMatrix('I', 5);


    public static void main(String[] args) throws Exception {
        


        Tarjan g1 = new Tarjan(5);
        g1.addEdge(0, 0);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(1, 1);
        g1.addEdge(1, 4);
        g1.addEdge(2, 1);
        g1.addEdge(2, 2);
        g1.addEdge(3, 3);
        g1.addEdge(3, 4);
        g1.addEdge(4, 3);
        g1.addEdge(4, 4);
        System.out.println(g1.SCC());
        // Print the SCCs
        Driver d = new Driver(new tempSparseMatrix(new File("text")), g1.SCC());
        d.mapSCCs();

        d.buildCondensedMatrix();
        d.computeTopologicalOrder();
        d.constructP();
        
        System.out.println(d);
    }
}