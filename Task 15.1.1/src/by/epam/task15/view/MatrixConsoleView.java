package by.epam.task15.view;

/**
 * Created by Владислав on 28.06.2016.
 */
public class MatrixConsoleView {

    public void render(double[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                System.out.printf("%8.3f ", matrix[i][j]);
            }
            System.out.print('\n');
        }
    }
}
