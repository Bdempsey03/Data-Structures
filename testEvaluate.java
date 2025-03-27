public class testEvaluate {

    public static void main(String[] args) {
        int[] cols = {0, 1, 4, 6, 8, 11};
        int[] rows = {0, 0, 1, 2, 0, 2, 3, 4, 1, 3, 4};
        int[] perm = {4, 3, 1, 2, 0};
        PMatrix A = new PMatrix(rows, cols, perm);

        EvaluatorV2.evaluate(A);
        
//        int[] cols = {0,2,9,11,15,16,20,21,26,27,27,29};
//        int[] rows = {2,9,0,2,3,4,5,7,9,9,10,1,2,6,7,9,0,2,4,10,8,1,2,6,8,10,6,7,8};
//        int[] perm = {0,1,2,3,5,7,10,4,6,8,9};
//        PMatrix A = new PMatrix(rows, cols, perm);

//        EvaluatorV2.evaluate(A);

    }

}
