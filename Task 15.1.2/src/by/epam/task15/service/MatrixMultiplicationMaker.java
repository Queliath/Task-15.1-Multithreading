package by.epam.task15.service;

import java.util.ArrayList;

/**
 * Created by Владислав on 28.06.2016.
 */
public class MatrixMultiplicationMaker {

    public double[][] multiply(double[][] matrixA, double[][] matrixB) throws InterruptedException {
        if(matrixA.length != matrixB.length && matrixA[0].length != matrixB[0].length){
            return new double[0][0];
        }

        double[][] resultMatrix = new double[matrixA.length][matrixA.length];

        Thread evenRowsCalculatorThread = new Thread(new RowsCalculator(matrixA, matrixB, resultMatrix, true));
        Thread oddRowsCalculatorThread = new Thread(new RowsCalculator(matrixA, matrixB, resultMatrix, false));

        evenRowsCalculatorThread.start();
        oddRowsCalculatorThread.start();

        evenRowsCalculatorThread.join();
        oddRowsCalculatorThread.join();

        return resultMatrix;
    }

    private static class RowsCalculator implements Runnable{

        private volatile double[][] matrixA;
        private volatile double[][] matrixB;
        private volatile double[][] resultMatrix;

        private boolean evenRows;

        RowsCalculator() {
        }

        RowsCalculator(double[][] matrixA, double[][] matrixB, double[][] resultMatrix, boolean evenRows) {
            this.matrixA = matrixA;
            this.matrixB = matrixB;
            this.resultMatrix = resultMatrix;
            this.evenRows = evenRows;
        }

        @Override
        public void run() {
            int startRowNumber = 0;
            if(evenRows){
                startRowNumber = 1;
            }

            for(int r = startRowNumber; r < resultMatrix.length; r = r + 2){
                for(int c = 0; c < resultMatrix[r].length; c++){
                    double resultValue = 0.0;

                    for(int i = 0; i < matrixA.length; i++){
                        resultValue = resultValue + matrixA[r][i] * matrixB[i][c];
                    }

                    resultMatrix[r][c] = resultValue;
                }
            }
        }
    }
}
