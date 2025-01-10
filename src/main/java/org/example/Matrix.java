package org.example;

public class Matrix {
    public static final int MIN_SIZE = 1;

    public Matrix(int rowsCount, int colsCount) {
        if (rowsCount < MIN_SIZE) {
            throw new IllegalArgumentException();
        } else if (colsCount < MIN_SIZE) {
            throw new IllegalArgumentException();
        }
    }
}