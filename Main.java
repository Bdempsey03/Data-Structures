import java.io.File;
import java.util.List;

public class Main{



    private static SparseMatrix input1; //has getRow, getVal, getCol
    private static SparseMatrix input2;
    private static SparseMatrix input3;
    private static SparseMatrix input4;
    private static SparseMatrix input5;
    private static SparseMatrix input6;
    private static SparseMatrix input7;
    private static SparseMatrix input8;
    private static SparseMatrix input9;
    private static SparseMatrix input10;

    private static Stopwatch stopwatch = new Stopwatch();

    private static int[][] tarjanOutput; //output of Tarjan algorithm
    private static int[][] kosarajuOutput; //output of Kosaraju algorithm
    private SparseMatrix best;


    public static void main(String[] args) throws Exception {
        input1 = new SparseMatrix(new File("./Data/California.mtx"));
        input2 = new SparseMatrix(new File("./Data/EPA.mtx"));
        input3 = new SparseMatrix(new File("./Data/EVA.mtx"));
        input4 = new SparseMatrix(new File("./Data/GD98_a.mtx"));
        input5 = new SparseMatrix(new File("./Data/GD99_c.mtx"));
        input6 = new SparseMatrix(new File("./Data/GlossGT.mtx"));
        input7 = new SparseMatrix(new File("./Data/HEP-th-new.mtx"));
        input8 = new SparseMatrix(new File("./Data/HEP-th.mtx"));
        input9 = new SparseMatrix(new File("./Data/Tina_AskCal.mtx"));
        input10 = new SparseMatrix(new File("./Data/wb-cs-stanford.mtx"));

        Tarjan tarjan1 = new Tarjan(input1.getRows(), input1.getCols());
        Tarjan tarjan2 = new Tarjan(input2.getRows(), input2.getCols());
        Tarjan tarjan3 = new Tarjan(input3.getRows(), input3.getCols());
        Tarjan tarjan4 = new Tarjan(input4.getRows(), input4.getCols());
        Tarjan tarjan5 = new Tarjan(input5.getRows(), input5.getCols());
        Tarjan tarjan6 = new Tarjan(input6.getRows(), input6.getCols());
        Tarjan tarjan7 = new Tarjan(input7.getRows(), input7.getCols());
        Tarjan tarjan8 = new Tarjan(input8.getRows(), input8.getCols());
        Tarjan tarjan9 = new Tarjan(input9.getRows(), input9.getCols());
        Tarjan tarjan10 = new Tarjan(input10.getRows(), input10.getCols());

        Kosaraju kosaraju1 = new Kosaraju(input1.getRows(), input2.getCols());
        Kosaraju kosaraju2 = new Kosaraju(input2.getRows(), input2.getCols());
        Kosaraju kosaraju3 = new Kosaraju(input3.getRows(), input3.getCols());
        Kosaraju kosaraju4 = new Kosaraju(input4.getRows(), input4.getCols());
        Kosaraju kosaraju5 = new Kosaraju(input5.getRows(), input5.getCols());
        Kosaraju kosaraju6 = new Kosaraju(input6.getRows(), input6.getCols());
        Kosaraju kosaraju7 = new Kosaraju(input7.getRows(), input7.getCols());
        Kosaraju kosaraju8 = new Kosaraju(input8.getRows(), input8.getCols());
        Kosaraju kosaraju9 = new Kosaraju(input9.getRows(), input9.getCols());
        Kosaraju kosaraju10 = new Kosaraju(input10.getRows(), input10.getCols());



        
        System.out.println("Tarjan Algorithm");
        Tarjan[] tarjan = {tarjan1, tarjan2, tarjan3, tarjan4, tarjan5, tarjan6, tarjan7, tarjan8, tarjan9, tarjan10};
        Kosaraju[] kosaraju = {kosaraju1, kosaraju2, kosaraju3, kosaraju4, kosaraju5, kosaraju6, kosaraju7, kosaraju8, kosaraju9, kosaraju10};
        for(int i = 0; i < tarjan.length; i++){
            stopwatch.start();
            for(int j = 0; j < 100; j++)
                tarjan[i].SCC();
            stopwatch.stop();
            System.out.println("Time: " + stopwatch.elapsedTime());
            stopwatch.reset();
        }
        System.out.println("Kosaraju Algorithm");
        for(int i = 0; i < kosaraju.length; i++){
            stopwatch.start();
            for(int j = 0; j < 100; j++)
                kosaraju[i].runAlgorithm();
            stopwatch.stop();
            System.out.println("Time: " + stopwatch.elapsedTime());
            stopwatch.reset();
        }




    }

}