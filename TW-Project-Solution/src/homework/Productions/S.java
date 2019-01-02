package homework.Productions;


import homework.Matrix;

public class S extends Production {

    private Matrix M;
    private int i;
    private int j;
    private double div;

    public S(Matrix M, int i, int j) {
        super("" +i+j);
        this.M = M;
        this.i = i;
        this.j = j;
        this.div = M.matrix[i][i] / M.matrix[j][i];
    }

    @Override
    public void run()  {
        double [] matrixRow = new double[M.matrix[0].length];

        //calculate value to substract
        for(int k=0; k<matrixRow.length; k++) {
            matrixRow[k] = M.matrix[i][k]/div;
        }

        //substract proper values from elements in row
        for(int k=0; k<matrixRow.length; k++) {
            M.matrix[j][k] -= matrixRow[k];
        }
    }
}