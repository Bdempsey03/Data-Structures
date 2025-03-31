public class Evaluator {

	public static String evaluate(PMatrix pMatrix) {
        int tfm = 0; 
        int tfbd = 0; 
        int size = pMatrix.size;

        for (int m = 0; m < size; m++) {
            for (int n = m + 1; n < size; n++) { //iterate through elements above the diagonal of PMatrix
                if (pMatrix.getElementP(m, n) == 1) {
                    tfm++;
                    tfbd += (n - m);
                }
            }
        }

        System.out.println("TFM (Number of 1's above diagonal): " + tfm);
        System.out.println("TFBD (Total distance of 1's above diagonal from diagonal): " + tfbd);
        return "TFM: " + tfm + ", TFBD: " + tfbd + ",";
    }

    
}
