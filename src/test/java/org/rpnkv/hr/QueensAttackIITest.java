package org.rpnkv.hr;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class QueensAttackIITest {

    @Test
    void noObstaclesTest() throws Exception {
        assertEquals(9, new QueensAttackII().queensAttack(4, 0, 4, 4, new int[][]{}));
    }

    @Test
    void baseObstaclesTest() throws Exception {
        int[][] obstacles = new int[][]{
                {5, 5},
                {4, 2},
                {2, 3}
        };


        assertEquals(10, new QueensAttackII().queensAttack(5, 3, 4, 3, obstacles));
    }
}