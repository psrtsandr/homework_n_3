package org.example.tests;

import org.example.Matrix;
import org.junit.jupiter.api.Test;

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
}
