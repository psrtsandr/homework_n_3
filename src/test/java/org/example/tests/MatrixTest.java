package org.example.tests;

import org.example.Matrix;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {
    @Test
    public void constructorThrowsWhenSizeIsLessThanOne() {
        assertThrows(IllegalArgumentException.class, () -> new Matrix(0, 0));
        assertThrows(IllegalArgumentException.class, () -> new Matrix(-1, -1));
        assertThrows(IllegalArgumentException.class, () -> new Matrix(0, 1));
        assertThrows(IllegalArgumentException.class, () -> new Matrix(1, 0));
        assertThrows(IllegalArgumentException.class, () -> new Matrix(1, -1));
        assertThrows(IllegalArgumentException.class, () -> new Matrix(-1, 1));
    }

    @Test
    public void testRowsCount() {
        assertEquals(1, new Matrix(1, 1).rowsCount());
        assertEquals(2, new Matrix(2, 2).rowsCount());
        assertEquals(3, new Matrix(3, 3).rowsCount());
    }

    @Test
    public void testColsCount() {
        assertEquals(1, new Matrix(1, 1).colsCount());
        assertEquals(2, new Matrix(2, 2).colsCount());
        assertEquals(3, new Matrix(3, 3).colsCount());
    }

    @Test
    public void allElementsAreZeros() {
        var matrix = new Matrix(3, 3);
        for (int i = 0; i < matrix.rowsCount(); i++) {
            for (int j = 0; j < matrix.colsCount(); j++) {
                assertEquals(0d, matrix.get(i, j));
            }
        }
    }

    @Test
    public void testCreateMatrixFrom2dList() {
        var values = Arrays.asList(
                Arrays.asList(1d, 2d, 3d),
                Arrays.asList(4d, 5d, 6d),
                Arrays.asList(7d, 8d, 9d)
        );
        var rowsCountExpected = 3;
        var colsCountExpected = 3;
        var matrix = new Matrix(values);
        assertEquals(rowsCountExpected, matrix.rowsCount());
        assertEquals(colsCountExpected, matrix.colsCount());
        for (int i = 0; i < values.size(); i++) {
            for (int j = 0; j < values.get(i).size(); j++) {
                assertEquals(values.get(i).get(j), matrix.get(i, j));
            }
        }
    }

    @Test
    public void testSet() {
        var matrix = new Matrix(2, 2);
        int row = 0, col = 0;
        var expectedValue = 2d;
        matrix.set(row, col, expectedValue);
        assertEquals(expectedValue, matrix.get(row, col));
    }

    @Test
    public void getThrowsWhenIndexIsInvalid() {
        var matrix = new Matrix(3, 3);
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(-1, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(Integer.MIN_VALUE, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(0, Integer.MIN_VALUE));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(0, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.get(3, 0));
    }

    @Test
    public void addThrowsWhenMatrixSizesDoNotMatch() {
        var a = new Matrix(2, 3);
        var b = new Matrix(3, 2);
        assertThrows(IllegalArgumentException.class, () -> Matrix.add(a, b));
    }

    @Test
    public void testAdd() {
        var a = new Matrix(Arrays.asList(
                Arrays.asList(1d, 2d, 3d),
                Arrays.asList(1d, 2d, 3d),
                Arrays.asList(1d, 2d, 3d)
        ));
        var b = new Matrix(Arrays.asList(
                Arrays.asList(7d, 8d, 9d),
                Arrays.asList(7d, 8d, 9d),
                Arrays.asList(7d, 8d, 9d)
        ));
        var expected = Arrays.asList(
                Arrays.asList(8d, 10d, 12d),
                Arrays.asList(8d, 10d, 12d),
                Arrays.asList(8d, 10d, 12d)
        );
        var actual = Matrix.add(a, b);
        for (int i = 0; i < expected.size(); i++) {
            for (int j = 0; j < expected.get(i).size(); j++) {
                assertEquals(expected.get(i).get(j), actual.get(i, j));
            }
        }
    }
}
