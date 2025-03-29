import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class Main {

    private static SparseMatrix input1; // has getRow, getVal, getCol
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

    private static int[][] tarjanOutput; // output of Tarjan algorithm
    private static int[][] kosarajuOutput; // output of Kosaraju algorithm
    private static String[] filePaths = { "./Data/California.mtx", "./Data/EPA.mtx", "./Data/EVA.mtx",
            "./Data/GD98_a.mtx", "./Data/GD99_c.mtx", "./Data/GlossGT.mtx", "./Data/HEP-th-new.mtx",
            "./Data/HEP-th.mtx", "./Data/Tina_AskCal.mtx", "./Data/wb-cs-stanford.mtx" };

    public static void main(String[] args) throws Exception {


        input1 = new SparseMatrix(new File(filePaths[0]));
        input2 = new SparseMatrix(new File(filePaths[1]));
        input3 = new SparseMatrix(new File(filePaths[2]));
        input4 = new SparseMatrix(new File(filePaths[3]));
        input5 = new SparseMatrix(new File(filePaths[4]));
        input6 = new SparseMatrix(new File(filePaths[5]));
        input7 = new SparseMatrix(new File(filePaths[6]));
        input8 = new SparseMatrix(new File(filePaths[7]));
        input9 = new SparseMatrix(new File(filePaths[8]));
        input10 = new SparseMatrix(new File(filePaths[9]));

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

        Kosaraju kosaraju1 = new Kosaraju(input1.getRows(), input1.getCols());
        Kosaraju kosaraju2 = new Kosaraju(input2.getRows(), input2.getCols());
        Kosaraju kosaraju3 = new Kosaraju(input3.getRows(), input3.getCols());
        Kosaraju kosaraju4 = new Kosaraju(input4.getRows(), input4.getCols());
        Kosaraju kosaraju5 = new Kosaraju(input5.getRows(), input5.getCols());
        Kosaraju kosaraju6 = new Kosaraju(input6.getRows(), input6.getCols());
        Kosaraju kosaraju7 = new Kosaraju(input7.getRows(), input7.getCols());
        Kosaraju kosaraju8 = new Kosaraju(input8.getRows(), input8.getCols());
        Kosaraju kosaraju9 = new Kosaraju(input9.getRows(), input9.getCols());
        Kosaraju kosaraju10 = new Kosaraju(input10.getRows(), input10.getCols());

        Evaluator evaluator1 = new Evaluator(input1, tarjan1.SCC());
        Evaluator evaluator2 = new Evaluator(input2, tarjan2.SCC());
        Evaluator evaluator3 = new Evaluator(input3, tarjan3.SCC());
        Evaluator evaluator4 = new Evaluator(input4, tarjan4.SCC());
        Evaluator evaluator5 = new Evaluator(input5, tarjan5.SCC());
        Evaluator evaluator6 = new Evaluator(input6, tarjan6.SCC());
        Evaluator evaluator7 = new Evaluator(input7, tarjan7.SCC());
        Evaluator evaluator8 = new Evaluator(input8, tarjan8.SCC());
        Evaluator evaluator9 = new Evaluator(input9, tarjan9.SCC());
        Evaluator evaluator10 = new Evaluator(input10, tarjan10.SCC());

        Evaluator evaluator11 = new Evaluator(input1, kosaraju1.runAlgorithm());
        Evaluator evaluator12 = new Evaluator(input2, kosaraju2.runAlgorithm());
        Evaluator evaluator13 = new Evaluator(input3, kosaraju3.runAlgorithm());
        Evaluator evaluator14 = new Evaluator(input4, kosaraju4.runAlgorithm());
        Evaluator evaluator15 = new Evaluator(input5, kosaraju5.runAlgorithm());
        Evaluator evaluator16 = new Evaluator(input6, kosaraju6.runAlgorithm());
        Evaluator evaluator17 = new Evaluator(input7, kosaraju7.runAlgorithm());
        Evaluator evaluator18 = new Evaluator(input8, kosaraju8.runAlgorithm());
        Evaluator evaluator19 = new Evaluator(input9, kosaraju9.runAlgorithm());
        Evaluator evaluator20 = new Evaluator(input10, kosaraju10.runAlgorithm());


        try (FileWriter writer = new FileWriter("output.csv")) {

            System.out.println("Tarjan Algorithm (averaged over 1000 runs)");
            writer.write("Tarjan,\n");
            writer.write("n,Time(ms),File,\n");
            SparseMatrix[] input = { input1, input2, input3, input4, input5, input6, input7, input8, input9, input10 };
            Tarjan[] tarjan = { tarjan1, tarjan2, tarjan3, tarjan4, tarjan5, tarjan6, tarjan7, tarjan8, tarjan9,
                    tarjan10 };
            Kosaraju[] kosaraju = { kosaraju1, kosaraju2, kosaraju3, kosaraju4, kosaraju5, kosaraju6, kosaraju7,
                    kosaraju8, kosaraju9, kosaraju10 };



            //TESTS
            for(int i = 0; i < 10; i++){
                if(tarjan[i].SCC().size() == kosaraju[i].runAlgorithm().size())
                    System.out.println("\u001B[32m Test " + i + " passed, tarjan[i].SCC().size() == kosaraju[i].runAlgorithm().size()\u001B[0m");
                else
                    System.out.println("\u001B[31m Test " + i + " failed, tarjan[i].SCC().size() != kosaraju[i].runAlgorithm().size()\u001B[0m");
                }

            for (int i = 0; i < tarjan.length; i++) {
                stopwatch.start();
                for (int j = 0; j < 10; j++)
                    tarjan[i].SCC();
                stopwatch.stop();
                writer.write(
                        input[i].getCols().length + "," + stopwatch.elapsedTime() / 1000 + "," + filePaths[i] + ",\n");
                System.out.println(stopwatch.elapsedTime() / 1000 + "ms for " + filePaths[i]);
                stopwatch.reset();
            }
            System.out.println("Kosaraju Algorithm (averaged over 1000 runs)");
            writer.write("Kosaraju,\n");
            writer.write("n,Time(ms),File,\n");
            for (int i = 0; i < kosaraju.length; i++) {
                stopwatch.start();
                for (int j = 0; j < 10; j++) {
                    kosaraju[i].runAlgorithm();
                }
                stopwatch.stop();
                writer.write(
                        input[i].getCols().length + "," + stopwatch.elapsedTime() / 1000 + "," + filePaths[i] + ",\n");
                System.out.println(stopwatch.elapsedTime() / 1000 + "ms for " + filePaths[i]);
                stopwatch.reset();
            }
            for(int i = 0; i < 10; i++){
                System.out.println(filePaths[i] + " Tarjan Algorithm: ");
                EvaluatorV2.evaluate(new PMatrix(input[i].getRows(), input[i].getCols(), makePerm(input[i], tarjan[i].SCC())));
            }
            for(int i = 0; i < 10; i++){
                System.out.println(filePaths[i] + " Kosaraju Algorithm: ");
                EvaluatorV2.evaluate(new PMatrix(input[i].getRows(), input[i].getCols(), makePerm(input[i], kosaraju[i].runAlgorithm())));
            }

            System.out.println(kosaraju9.runAlgorithm() + "\n" + tarjan9.SCC() + "\n" + kosaraju9.runAlgorithm().size() + "\n" + tarjan9.SCC().size());
            


            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public static int[] makePerm(SparseMatrix original, ArrayList<SCC> SCCs){
        int[] perm = new int[original.getCols().length-1];
        for(SCC scc : SCCs){
            
            for(int i = 0; i < scc.nodes.size(); i++){
                perm[i] = scc.nodes.get(i);
            }
        }
        
       
        return perm;
    }

}