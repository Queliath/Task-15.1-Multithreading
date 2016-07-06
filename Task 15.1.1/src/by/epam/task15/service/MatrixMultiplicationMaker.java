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

        ArrayList<Thread> cellCalculatorsThreads = new ArrayList<>(resultMatrix.length * resultMatrix.length);

        for(int i = 0; i < resultMatrix.length; i++){
            for(int j = 0; j < resultMatrix[i].length; j++){
                Thread cellCalculatorThread = new Thread(new CellCalculator(matrixA, matrixB, resultMatrix, i, j));
                cellCalculatorsThreads.add(cellCalculatorThread);
            }
        }

        for(Thread thread : cellCalculatorsThreads){
            thread.start();
        }

        for(Thread thread : cellCalculatorsThreads){
            thread.join();
        }

        return resultMatrix;
    }

    private static class CellCalculator implements Runnable{

        private volatile double[][] matrixA;
        private volatile double[][] matrixB;
        private volatile double[][] resultMatrix;

        private int rowNumber;
        private int columnNumber;

        CellCalculator() {
        }

        CellCalculator(double[][] matrixA, double[][] matrixB, double[][] resultMatrix, int rowNumber, int columnNumber) {
            this.matrixA = matrixA;
            this.matrixB = matrixB;
            this.resultMatrix = resultMatrix;
            this.rowNumber = rowNumber;
            this.columnNumber = columnNumber;
        }

        @Override
        public void run() {
            double resultValue = 0.0;

            for(int i = 0; i < matrixA.length; i++){
                resultValue = resultValue + matrixA[rowNumber][i] * matrixB[i][columnNumber];
            }

            resultMatrix[rowNumber][columnNumber] = resultValue;
        }
    }
}
