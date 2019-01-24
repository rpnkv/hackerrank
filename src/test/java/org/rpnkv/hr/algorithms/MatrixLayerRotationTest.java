package org.rpnkv.hr.algorithms;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MatrixLayerRotationTest {

    @Test
    void sampleInput1() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(13, 9, 5, 1));
        matrix.add(Arrays.asList(14, 10, 6, 2));
        matrix.add(Arrays.asList(15, 11, 7, 3));
        matrix.add(Arrays.asList(16, 12, 8, 4));

        MatrixLayerRotation mlr = new MatrixLayerRotation(2, 4, 4, matrix);
        mlr.printFinalMatrix();
    }
}