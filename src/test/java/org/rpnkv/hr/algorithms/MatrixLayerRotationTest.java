package org.rpnkv.hr.algorithms;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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

    @Test
    void noEffectiveRotation() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(1, 2, 3, 4));
        matrix.add(Arrays.asList(5, 6, 7, 8));
        matrix.add(Arrays.asList(9, 10, 11, 12));
        matrix.add(Arrays.asList(13, 14, 15, 16));

        MatrixLayerRotation mlr = new MatrixLayerRotation(13, 4, 4, matrix);
        mlr.printFinalMatrix();
    }

    @Test
    void rotateStayingRectangle() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(1, 2, 3, 4));
        matrix.add(Arrays.asList(5, 6, 7, 8));
        matrix.add(Arrays.asList(9, 10, 11, 12));
        matrix.add(Arrays.asList(13, 14, 15, 16));
        matrix.add(Arrays.asList(17, 18, 19, 20));

        MatrixLayerRotation mlr = new MatrixLayerRotation(1, 4, 5, matrix);
        mlr.printFinalMatrix();
    }

    @Test
    void rotateLayingRectangle() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(1, 2, 3, 4, 5));
        matrix.add(Arrays.asList(6, 7, 8, 9, 10));
        matrix.add(Arrays.asList(11, 12, 13, 14, 15));
        matrix.add(Arrays.asList(16, 17, 18, 19, 10));

        MatrixLayerRotation mlr = new MatrixLayerRotation(1, 5, 4, matrix);
        mlr.printFinalMatrix();
    }

    @Test
    void inputTest() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] mnr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(mnr[0]);

        int n = Integer.parseInt(mnr[1]);

        int r = Integer.parseInt(mnr[2]);

        List<List<Integer>> matrix = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                matrix.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        MatrixLayerRotation mlr = new MatrixLayerRotation(r, m, n, matrix);
        mlr.printFinalMatrix();

        bufferedReader.close();
    }
}