package by.epam.task15.start;

import by.epam.task15.service.MatrixGenerator;
import by.epam.task15.service.MatrixMultiplicationMaker;
import by.epam.task15.view.MatrixConsoleView;

/**
 * Created by Владислав on 28.06.2016.
 */
public class Main {

    private static final int RANK = 10;

    public static void main(String[] args){
        MatrixGenerator matrixGenerator = new MatrixGenerator();
        double[][] matrixA, matrixB;

        matrixA = matrixGenerator.generate(RANK);
        matrixB = matrixGenerator.generate(RANK);

        MatrixMultiplicationMaker matrixMultiplicationMaker = new MatrixMultiplicationMaker();
        double[][] resultMatrix;
        try {
            resultMatrix = matrixMultiplicationMaker.multiply(matrixA, matrixB);
        } catch (InterruptedException e) {
            resultMatrix = new double[0][0];
        }

        MatrixConsoleView matrixConsoleView = new MatrixConsoleView();
        System.out.println("Матрица А:");
        matrixConsoleView.render(matrixA);
        System.out.println("Матрица B:");
        matrixConsoleView.render(matrixB);
        System.out.println("Результирующая матрица");
        matrixConsoleView.render(resultMatrix);
    }
}
