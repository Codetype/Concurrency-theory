package homework.Executor;

import homework.FoataGenerator.FoataClass;
import homework.InputHandler.FileHandler;
import homework.InputHandler.MatrixHandler;
import homework.Matrix;
import homework.Productions.P;
import homework.Productions.S;

import java.io.BufferedWriter;
import java.io.IOException;

public class Executor {
    private int n;
    private FoataClass f;
    private BufferedWriter writer;
    private MatrixHandler matrixHandler;
    private Matrix M;

    public Executor() throws IOException, InterruptedException {
        this.matrixHandler = MatrixHandler.getInstance();
        this.M = this.matrixHandler.getMatrix();

        this.n = this.M.matrix.length;

        this.writer = FileHandler.getInstance().getWriter();

        this.f = new FoataClass();
        this.execute();
    }

    private void execute() throws IOException, InterruptedException{
        //FIRST STEP
        for(int i=0;i<n;i++) {
            //HANDLE PROBLEM WITH ZERO ON DIAGONAL
            if(M.matrix[i][i] == 0) {
                M.solveDiagonalZero(i);
            }

            for(int j=0;j<n;j++) {
                if(j==i) continue;
                else {
                    if(M.matrix[j][i] != 0) {
                        f.push(new S(M, i, j));
                    }
                }
            }
            f.execute();
        }

        //SECOND STEP
        for(int i=0;i<n;i++) {
            f.push(new P(M, i));
        }
        f.execute();

        //save output
        this.matrixHandler.saveMatrix(M.matrix);
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("IOException occured");
        }
    }
}
