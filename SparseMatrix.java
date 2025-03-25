import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SparseMatrix {
    private int[] rows;
    private int[] cols;
    private int nnz;


    public SparseMatrix(File file) throws FileNotFoundException {

        Scanner sc = new Scanner(file);

        //Loop skips all formatting text and gets to actual matrix values
        String loop = sc.nextLine();
        while (loop.charAt(0) == '%') {
            loop = sc.nextLine();
        }

        //split initial matrix info to get dimensions and nnz count
        String[] matrix_info = loop.split(" ");
//      SparseMatrix matrix = new SparseMatrix(Integer.parseInt(matrix_info[0]), Integer.parseInt(matrix_info[1]), Integer.parseInt(matrix_info[2]));
        cols =  new int[Integer.parseInt(matrix_info[1]) + 2];
        rows = new int[Integer.parseInt(matrix_info[2])];
        nnz = Integer.parseInt(matrix_info[2]);


        ArrayList<Pair> coordinates = new ArrayList<>();

        //create pairs of nnz coordinates to be used later
        while (sc.hasNextLine()) {
            loop = sc.nextLine();
            String[] cord = loop.split(" ");
            Pair tuple = new Pair(Integer.parseInt(cord[0]), Integer.parseInt(cord[1]));
            coordinates.add(tuple);
        }

        sc.close();


        //below logic organizes nnz coordinates into csc format
        int y = 0;
        int count = 0;
        for (int x = 0; x < this.getCols().length; x++) {
            if(y < coordinates.size() && coordinates.get(y).getCol() == x) {
                while (y < coordinates.size() && coordinates.get(y).getCol() == x) {
                    count++;
                    if(this.getCols().length > x + 1) {
                        this.setCols(x + 1, count);
                    }

                    if(y < this.getRows().length) {
                        this.setRows(y, coordinates.get(y).getRow());
                    }
                    y++;
                }
            }else{
                if(this.getCols().length > x + 1) {
                    this.setCols(x + 1, count);
                }
            }

            if(y < this.getRows().length) {
                this.setRows(y, coordinates.get(y).getRow());
            }

        }
        // System.out.println(Arrays.toString(this.getRows()));
        // System.out.println(Arrays.toString(this.getCols()));
    }

    public int getNnz() {
        return nnz;
    }

    public void setNnz(int nnz) {
        this.nnz = nnz;
    }

    public int[] getCols() {
        return cols;
    }

    public void setCols(int index, int v) {;
        cols[index] = v;
    }

    public int[] getRows() {
        return rows;
    }

    public void setRows(int index, int v) {
        rows[index] = v;
    }
}