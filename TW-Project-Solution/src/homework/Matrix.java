package homework;

public class Matrix {
    private int M;
    private int N;
    public double[][] matrix;

    public Matrix(int M, int N){
        this.matrix = new double[M][N];
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public void setMatrixField(int i, int j, double v){
        matrix[i][j] = v;
    }

    public double getMatrixField(int i, int j){ return matrix[i][j]; }

    public void solveDiagonalZero(int i){
        int firstRow = i;
        int secondRow = i;

        for(int j=0; j<N; j++) {

            if(i != j && matrix[j][i] != 0.0) {
                secondRow = j;
                continue;
            }

            if(j==N-1) {
                throw new Error("Incorrect Input matrix");
            }
        }

        double [] matrixFirstRow = matrix[firstRow];
        double [] matrixSecondRow = matrix[secondRow];

        for(int k=0; k<N; k++) {
            matrix[firstRow][k] = matrixSecondRow[k];
            matrix[secondRow][k] = matrixFirstRow[k];
        }
    }
}
