package org.rpnkv.hr.datastructures.arrays;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ArrayManipulationTest {

    @Test
    void sampleTestCase0() {
        int[][] queries = new int[3][3];
        queries[0] = new int[]{1, 2, 100};
        queries[1] = new int[]{2, 5, 100};
        queries[2] = new int[]{3, 4, 100};


        ArrayManipulation arrayManipulation = new ArrayManipulation();
        assertEquals(200, arrayManipulation.arrayManipulation(5, queries));

    }

    @Test
    void sampleTestCase1() {
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

    @Test
    void finalTestCase0() {
        int[][] queries = new int[][]{
                {2, 3, 603},
                {1, 1, 286},
                {4, 4, 882}
        };
        ArrayManipulation am = new ArrayManipulation();
        assertEquals(882, am.arrayManipulation(4, queries));
    }

    @Test
    /**
     * Expected result - 2501448788
     */
    void stdin() {
        Scanner scanner = new Scanner(System.in);

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] queries = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        long result = new ArrayManipulation().arrayManipulation(n, queries);

        System.out.println(result);

        scanner.close();
    }
}