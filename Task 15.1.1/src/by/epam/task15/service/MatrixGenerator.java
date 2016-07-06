package by.epam.task15.service;

/**
 * Created by Владислав on 28.06.2016.
 */
public class MatrixGenerator {

    public double[][] generate(int rank){
        double[][] matrix = new double[rank][rank];

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                matrix[i][j] = Math.random();
            }
        }

        return matrix;
    }
}
