package org.rpnkv.hr.datastructures.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayManipulationTest {

    @Test
    void testCase0() {
        int[][] queries = new int[3][3];
        queries[0] = new int[]{1, 2, 100};
        queries[1] = new int[]{2, 5, 100};
        queries[2] = new int[]{3, 4, 100};


        ArrayManipulation arrayManipulation = new ArrayManipulation();
        assertEquals(200, arrayManipulation.arrayManipulation(5, queries));

    }

    @Test
    void testCase1() {
        int[][] queries = new int[][]{
                {1, 5, 3},
                {4, 8, 7},
                {6, 9, 1}
        };

        ArrayManipulation am = new ArrayManipulation();
        assertEquals(10, am.arrayManipulation(10, queries));
    }

    @Test
    void testCase2() {
        int[][] queries = new int[][]{
                {2, 6, 8},
                {3, 5, 7},
                {1, 8, 1},
                {5, 9, 15}
        };

        ArrayManipulation am = new ArrayManipulation();
        assertEquals(31, am.arrayManipulation(10, queries));
    }
}