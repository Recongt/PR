package com.emil.pr;

import Jama.*;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;

public class NormalizationTest {
    
    private double myNorm(double[][] matrix1, double[][] matrix2) {
        double length = matrix1.length;
        
        if(length != matrix2.length)
            throw new UnsupportedOperationException();
        
        if(length == 1)
            return Math.abs(matrix1[0][0] - matrix2[0][0]);
        else {
            double result = 0.0;
            for(int i = 0; i < length; ++i)
                result += Math.pow(matrix1[i][0] - matrix2[i][0], 2);
            
            return Math.sqrt(result);
        }
    }
    
    private double jamaNorm(Matrix matrix1, Matrix matrix2) {
        return matrix1.minus(matrix2).norm2();
    }
    
    @Test
    public void singleValueVector() {
        //Arrange
        double[][]  matrixValues1 = makeMatrixFromVector(5);
        Matrix matrix1 = Matrix.constructWithCopy(matrixValues1);
        
        double[][]  matrixValues2 = makeMatrixFromVector(9);
        Matrix matrix2 = Matrix.constructWithCopy(matrixValues2);
        
        //Act
        double myNormResult = myNorm(matrixValues1, matrixValues2);
        double jamaNormResult = jamaNorm(matrix1, matrix2);
        
        //Assert
        assertEquals(myNormResult, jamaNormResult, 0.0001);
    }
    
    @Test
    public void twoValuesVector() {
        //Arrange
        double[][] matrixValues1 = makeMatrixFromVector(3, 7);
        Matrix matrix1 = Matrix.constructWithCopy(matrixValues1);
        
        double[][] matrixValues2 = makeMatrixFromVector(11,5);
        Matrix matrix2 = Matrix.constructWithCopy(matrixValues2);
        
        //Act
        double myNormResult = myNorm(matrixValues1, matrixValues2);
        double jamaNormResult = jamaNorm(matrix1, matrix2);
        
        //Assert
        assertEquals(myNormResult, jamaNormResult, 0.0001);
    }
    
    @Test
    public void threeValuesVector() {
        //Arrange
        double[][] matrixValues1 = makeMatrixFromVector(700, 5, 3);
        Matrix matrix1 = Matrix.constructWithCopy(matrixValues1);
        
        double[][] matrixValues2 = makeMatrixFromVector(3, 7, 11);
        Matrix matrix2 = Matrix.constructWithCopy(matrixValues2);
        
        //Act
        double myNormResult = myNorm(matrixValues1, matrixValues2);
        double jamaNormResult = jamaNorm(matrix1, matrix2);
        
        //Assert
        assertEquals(myNormResult, jamaNormResult, 0.0001);
    }
    
    @Test
    public void fourValuesVector() {
        //Arrange
        double[][] matrixValues1 = makeMatrixFromVector(4, 8, 3, 4);
        Matrix matrix1 = Matrix.constructWithCopy(matrixValues1);
        
        double[][] matrixValues2 = makeMatrixFromVector(9, 0, 300, 1);
        Matrix matrix2 = Matrix.constructWithCopy(matrixValues2);
        
        //Act
        double myNormResult = myNorm(matrixValues1, matrixValues2);
        double jamaNormResult = jamaNorm(matrix1, matrix2);
        
        //Assert
        assertEquals(myNormResult, jamaNormResult, 0.0001);
    }
    
    private double[][] makeMatrixFromVector(double...values) {
        int length = values.length;
        double[][] matrix = new double[length][1];
        
        for(int i = 0; i < length; ++i)
            matrix[i][0] = values[i];
        
        return matrix;
    }
    
}
