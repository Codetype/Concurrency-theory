package homework.Productions;

import homework.Matrix;

public class P extends Production{
    private Matrix M;
    private int i;

    public P(Matrix M, int i) {
        super(""+i);
        this.M = M;
        this.i = i;
    }

    @Override
    public void run() {
        int n = M.matrix[0].length;

        double div = M.matrix[i][i];
        //set apropriate values of B column
        M.matrix[i][n-1] /= div;

        //set 1.0 on diagonal
        M.matrix[i][i] = 1.0;

        //clean values below 1e-10, which was calculated during dividing small numbers, set to 0
        for(int k=0; k<n; k++) {
            if(Math.abs(M.matrix[i][k]) < Math.pow(10, -10)) {
                M.matrix[i][k] = 0.0;
            }
        }
        return;
    }
}
