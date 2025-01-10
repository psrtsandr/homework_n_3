package org.example;

import java.util.Collection;
import java.util.List;

public class Matrix {
    public static final int MIN_SIZE = 1;

    private final int rowsCount;
    private final int colsCount;
    private final double[] values;

    public Matrix(int rowsCount, int colsCount) {
        if (rowsCount < MIN_SIZE) {
            throw new IllegalArgumentException();
        } else if (colsCount < MIN_SIZE) {
            throw new IllegalArgumentException();
        }
        this.rowsCount = rowsCount;
        this.colsCount = colsCount;
        values = new double[rowsCount * colsCount];
    }

    public Matrix(List<List<Double>> values) {
        if (!isValid(values)) {
            throw new IllegalArgumentException();
        }
        rowsCount = values.size();
        colsCount = values.getFirst().size();
        this.values = values.stream()
                .flatMap(Collection::stream)
                .mapToDouble(Double::doubleValue)
                .toArray();
    }

    public static Matrix add(Matrix a, Matrix b) {
        if (a.rowsCount != b.rowsCount || a.colsCount != b.colsCount) {
            throw new IllegalArgumentException();
        }
        var sum = new Matrix(a.rowsCount, a.colsCount);
        for (int i = 0; i < a.rowsCount; i++) {
            for (int j = 0; j < a.colsCount; j++) {
                sum.set(i, j, a.get(i, j) + b.get(i, j));
            }
        }
        return sum;
    }

    public static Matrix subtract(Matrix a, Matrix b) {
        if (a.rowsCount != b.rowsCount || a.colsCount != b.colsCount) {
            throw new IllegalArgumentException();
        }
        var diff = new Matrix(a.rowsCount, a.colsCount);
        for (int i = 0; i < diff.rowsCount; i++) {
            for (int j = 0; j < diff.colsCount; j++) {
                diff.set(i, j, a.get(i, j) - b.get(i, j));
            }
        }
        return diff;
    }

    private boolean isValid(List<List<Double>> values) {
        return  values != null &&
                values.size() >= MIN_SIZE &&
                values.getFirst().size() >= MIN_SIZE &&
                values.stream()
                        .allMatch(row -> row.size() == values.getFirst().size());
    }

    public int rowsCount() {
        return rowsCount;
    }

    public int colsCount() {
        return colsCount;
    }

    public double get(int rowIndex, int colIndex) {
        if (rowIndex < 0 || rowIndex >= rowsCount) {
            throw new IndexOutOfBoundsException();
        } else if (colIndex < 0 || colIndex >= colsCount) {
            throw new IndexOutOfBoundsException();
        }
        int linearIndex = getLinearIndex(rowIndex, colIndex);
        return values[linearIndex];
    }

    private int getLinearIndex(int rowIndex, int colIndex) {
        return colIndex + rowIndex * rowsCount;
    }

    public void set(int rowIndex, int colIndex, double value) {
        if (rowIndex < 0 || rowIndex >= rowsCount) {
            throw new IndexOutOfBoundsException();
        } else if (colIndex < 0 || colIndex >= colsCount) {
            throw new IndexOutOfBoundsException();
        }
        int linearIndex = getLinearIndex(rowIndex, colIndex);
        values[linearIndex] = value;
    }
}