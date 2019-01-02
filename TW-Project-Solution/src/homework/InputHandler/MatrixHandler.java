package homework.InputHandler;

import homework.Matrix;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class MatrixHandler {


    private static MatrixHandler instance = new MatrixHandler();
    private FileHandler fileHandler;
    private BufferedWriter writer;
    private Matrix M;

    private MatrixHandler() {
        this.fileHandler = FileHandler.getInstance();
        this.writer = this.fileHandler.getWriter();

        String line = "";
        String fileName = "./src/input/input-com.txt";

        try {
            FileReader fileReader = new FileReader(fileName);

            @SuppressWarnings("resource")
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String firstLine = bufferedReader.readLine();

            int n = Integer.parseInt(firstLine);
            M = new Matrix(n, n+1);


            for(int i=0; i != n; i++) {
                line = bufferedReader.readLine();
                double currentRow[] = parseMatrixRow(line);

                if(currentRow.length != n) {
                    throw new Exception("Bad format of input");
                }

                for(int j=0; j<n; j++) {
                    M.matrix[i][j] = currentRow[j];
                }
            }

            line = bufferedReader.readLine();
            double resultRow[] = parseMatrixRow(line);

            for(int i=0;i<n;i++) {
                M.matrix[i][n] = resultRow[i];
            }

            bufferedReader.close();
        }
        catch(NumberFormatException e) {
            System.out.println("NumberFormatException occured");
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Matrix getMatrix() throws IOException {
        return this.M;
    }

    private double[] parseMatrixRow(String strLine) {
        double[] a;

        String[] split = strLine.split(" ");
        a = new double[split.length];

        for(int i = 0; i < a.length; i++){
            a[i] = Double.parseDouble(split[i]);
        }

        return a;
    }


    public void saveMatrix(double[][] matrix) throws IOException {
        String str;
        this.writer.write(matrix.length + "\n");

        for(int w=0; w<matrix.length; w++) {
            for(int k=0; k<matrix.length; k++) {
                str = "";
                str = matrix[w][k] + " ";
                this.writer.write(str);
            }
            this.writer.newLine();
        }

        for(int w=0; w<matrix.length; w++){
            str = matrix[w][matrix[0].length-1] + " ";
            this.writer.write(str);
        }

        this.writer.newLine();
    }

    public static MatrixHandler getInstance() {
        return instance;
    }
}
