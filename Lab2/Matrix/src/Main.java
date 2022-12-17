import exceptions.FileAccess;
import exceptions.TooHighValue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Main {

    public static Logger logger;
    static {
        try (FileInputStream fileInputStream = new FileInputStream("src/resources/log.config")) {
            LogManager.getLogManager().readConfiguration(fileInputStream);
            logger = Logger.getLogger(Main.class.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Matrix matrix = new Matrix();

        FileWriter fileWriterFirst = null;
        try {
            File file = new File("data/Output.txt");
            if (!file.canWrite()) {
                throw new FileAccess();
            } else {
                fileWriterFirst = new FileWriter(file);
                fileWriterFirst.write(String.format("Matrix has %d dimensions.%n", matrix.getDim()));
            }
        } catch (FileAccess e) {
            System.out.println(e);
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriterFirst != null) {
                    fileWriterFirst.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (
                Scanner scanner = new Scanner(new File("data/MatrixDimensions.txt"))
        ) {
            if (scanner.hasNextInt()){
                int N = scanner.nextInt();
                if (N == 0){
                    System.out.println("Matrix cannot have dimension 0!!!");
                    System.exit(1);
                } else if (N >= 1_000_000) {
                    throw new TooHighValue();
                } else {
                    matrix.setDim(N);
                }

            } else {
                System.out.println("File must have int value!!!");
                System.exit(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TooHighValue e) {
            System.out.println(e);
            System.exit(1);
        }

        try {
            matrix.genMatrix();
        } catch (OutOfMemoryError e) {
            logger.log(Level.SEVERE, "Catch exception " + e);
            System.exit(-1);
        }
        matrix.printMatrix();
        matrix.printStatistics();

        try (FileWriter fileWriter = new FileWriter("data/Output.txt", true)){
            matrix.writeMatrix(fileWriter);
        } catch (IOException e){
            System.out.println("Output file does not exist!!!");
        }

        matrix.rotateMatrix90();
        matrix.divByNeighbors();
        matrix.printStatistics();

        try (FileWriter fileWriter = new FileWriter("data/Output.txt", true)){
            matrix.writeMatrix(fileWriter);
        } catch (IOException e) {
            System.out.println("Output file does not exist!!!");
        }

        matrix.rotateMatrix180();
        matrix.divByNeighbors();
        matrix.printStatistics();

        try (FileWriter fileWriter = new FileWriter("data/Output.txt", true)){
            matrix.writeMatrix(fileWriter);
        } catch (IOException e){
            System.out.println("Output file does not exist!!!");
        }

        matrix.rotateMatrix270();
        matrix.divByNeighbors();
        matrix.printStatistics();

        try (FileWriter fileWriter = new FileWriter("data/Output.txt", true)){
            matrix.writeMatrix(fileWriter);
        } catch (IOException e){
            System.out.println("Output file does not exist!!!");
        }

        System.out.println("Done");
    }
}
