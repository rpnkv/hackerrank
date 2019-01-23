package org.rpnkv.hr.algorithms;

import java.util.List;

/**
 * https://www.hackerrank.com/challenges/matrix-rotation-algo/problem
 */
public class MatrixLayerRotation {

    private int r, columns, rows;
    private int[][] sourceMatrix, finalMatrix;

    public MatrixLayerRotation(int rotations, int length, int height, List<List<Integer>> matrix) {
        this.r = rotations;
        columns = length;
        rows = height;
        sourceMatrix = new int[length][height];
        finalMatrix = new int[length][height];

        for (int i = 0; i < columns; i++) {
            List<Integer> integers = matrix.get(i);
            for (int j = 0; j < rows; j++) {
                sourceMatrix[i][j] = integers.get(j);
            }
        }
    }

    public void printFinalMatrix() {

        int lessDimension = Math.min(columns, rows);
        for (int i = 0; i < lessDimension / 2 + 1; i++) {
            LayerRevolver layerRevolver = new LayerRevolver(i, r);
            layerRevolver.revolve();
        }
    }

    private void rotateElement(int length, int height, int rotations) {
    }

    class LayerRevolver {
        private final int layer, rotations, lC, lR;

        public LayerRevolver(int layer, int rotations) {
            this.layer = layer;

            lC = columns - layer * 2;
            lR = rows - layer * 2;

            this.rotations = rotations % (lR + lC);
        }

        public void revolve() {

        }

        private void revolveElement(int c, int r) {
            int rotations = this.rotations;

            while (rotations != 0) {//remove
                if (r == layer && c < layer + lC) {//a
                    rotations = rotateByA(r, c, rotations);
                    if (rotations != 0) {//set position to start b
                        c = layer + lC;
                        continue;
                    } else {
                        break;
                    }
                }
                if (c == layer + lC && r < layer + lR) {//b
                    rotations = rotateByB(r, c, rotations);
                    if (rotations != 0) {
                        r = layer + lR;
                        continue;
                    } else {
                        break;
                    }
                }
                if (c > layer + 1 && r == layer + lR) {//c
                    rotations = rotateByC(r, c, rotations);
                    if (rotations != 0) {
                        c = layer + lC;
                        r = layer;
                        continue;
                    } else {
                        break;
                    }
                }
                if (c == 1 && r > layer) {//d
                    rotations = rotateByD(r, c, rotations);
                    if (rotations != 0) {
                        throw new IllegalStateException("Too many rotations");
                    } else {
                        break;
                    }
                }
                throw new IllegalStateException(c + " " + r);
            }

        }

        private int rotateByA(int r, int c, int rotations) {
            return 0;
        }

        private int rotateByB(int r, int c, int rotations) {
            return 0;
        }

        private int rotateByC(int r, int c, int rotations) {
            return 0;
        }

        private int rotateByD(int r, int c, int rotations) {
            return 0;
        }
    }

}
