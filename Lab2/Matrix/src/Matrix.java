import exceptions.DivByZero;

import java.io.FileWriter;
import java.io.IOException;

import static java.lang.Math.random;

public class Matrix {
    private double[][] matrix;
    private int dim;
    private int outOfBounds;
    private int divByZero;

    public void setDim(int N) {this.dim = N;}
    public int getDim() {return this.dim;}

    public void genMatrix(){
        double[][] matrix = new double[this.dim][];
        for (int i = 0; i < this.dim; ++i) {
            matrix[i] = new double[this.dim];
            for (int j = 0; j < this.dim; ++j) {
                matrix[i][j] = (random() * (this.dim * 2) - this.dim);
            }
        }
        this.matrix = matrix;
    }

    public void rotateMatrix90(){
        double[][] matrix = new double[this.dim][];
        int k = this.dim;

        for (int i = 0; i < this.dim; ++i){
            k -= 1;
            matrix[i] = new double[this.dim];

            for (int j = 0; j < this.dim; ++j){
                matrix[i][j] = this.matrix[j][k];
            }
        }
        this.matrix = matrix;
    }

    public void rotateMatrix180(){
        double[][] matrix = new double[this.dim][];
        int k = this.dim;

        for (int i = 0; i < this.dim; ++i){
            int l = this.dim;
            k -= 1;
            matrix[i] = new double[this.dim];

            for (int j = 0; j < this.dim; ++j){
                l -= 1;
                matrix[i][j] = this.matrix[k][l];
            }
        }
        this.matrix = matrix;
    }

    public void rotateMatrix270(){
        double[][] matrix = new double[this.dim][];

        for (int i = 0; i < this.dim; ++i){
            int k = this.dim;
            matrix[i] = new double[this.dim];

            for (int j = 0; j < this.dim; ++j){
                k -= 1;
                matrix[i][j] = this.matrix[k][i];
            }
        }
        this.matrix = matrix;
    }

    public void divByNeighbors(){
        this.outOfBounds = 0;
        this.divByZero = 0;
        double[][] matrix = new double[this.dim][];

        for (int i = 0; i < this.dim; ++i){
            matrix[i] = new double[this.dim];

            for (int j = 0; j < this.dim; ++j){
                double sum = 0;
                int sumCase = 0;

                while (sumCase < 4) {
                    try {
                        switch (sumCase) {
                            case 0 -> sum += this.matrix[i - 1][j];
                            case 1 -> sum += this.matrix[i + 1][j];
                            case 2 -> sum += this.matrix[i][j - 1];
                            case 3 -> sum += this.matrix[i][j + 1];
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        ++this.outOfBounds;
                    }
                    ++sumCase;
                }

                try {
                    if (sum == 0){
                        matrix[i][j] = this.matrix[i][j];
                        throw new DivByZero();
                    } else {
                        matrix[i][j] = this.matrix[i][j] / sum;
                    }
                } catch (DivByZero e) {
                    System.out.println(e);
                    ++this.divByZero;
                }
            }
        }
        this.matrix = matrix;
    }

    public void printMatrix(){
        System.out.printf("Matrix %d X %d dimensions:%n", this.dim, this.dim);
        for (int i = 0; i < this.dim; ++i){
            for (int j = 0; j < this.dim; ++j) {
                System.out.printf("%20f ", this.matrix[i][j]);
            }
            System.out.println();
        }
    }

    public void printOutOfBounds(){
        System.out.printf("%nDetected %d ArrayIndexOutOfBoundsExceptions.%n", this.outOfBounds);
    }

    public void printDivByZero(){
        System.out.printf("Detected %d divide by zero operations.%n", this.divByZero);
    }

    public void printStatistics() {
        printOutOfBounds();
        printDivByZero();
    }

    public void writeMatrix(FileWriter writer) throws IOException {
        writer.write(String.format("%nMatrix %d X %d dimensions:%n", this.dim, this.dim));
        for (int i = 0; i < this.dim; ++i){
            for (int j = 0; j < this.dim; ++j) {
               writer.write(String.format("%20f", this.matrix[i][j]));
            }
            writer.write("\n");
        }
    }
}
