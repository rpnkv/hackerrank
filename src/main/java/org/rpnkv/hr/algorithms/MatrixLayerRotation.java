package org.rpnkv.hr.algorithms;

import java.util.List;

/**
 * https://www.hackerrank.com/challenges/matrix-rotation-algo/problem
 */
public class MatrixLayerRotation {

    private int r, columns, rows, el;
    private final int layers;
    private int[][] sourceMatrix, finalMatrix;

    public MatrixLayerRotation(int rotations, int length, int height, List<List<Integer>> matrix) {
        this.r = rotations;
        columns = length;
        rows = height;
        sourceMatrix = new int[length][height];
        finalMatrix = new int[length][height];

        for (int row = 0; row < rows; row++) {
            List<Integer> integers = matrix.get(row);
            for (int column = 0; column < columns; column++) {
                sourceMatrix[column][rows - 1 - row] = integers.get(column);
            }
        }

        int lessDimension = Math.min(columns, rows);
        layers = lessDimension / 2;
    }

    public void printFinalMatrix() {
        printMatrix(sourceMatrix);
        System.out.println();


        for (int i = 0; i < layers; i++) {
            LayerRevolver layerRevolver = new LayerRevolver(i, r);
            layerRevolver.revolve();
        }


        printMatrix(finalMatrix);
    }

    private void printMatrix(int[][] finalMatrix) {
        for (int r = rows - 1; r >= 0; r--) {
            for (int c = 0; c < columns; c++) {
                System.out.print(finalMatrix[c][r] + " ");
            }
            System.out.println();
        }
    }

    class LayerRevolver {
        private final int layer;
        private final int rotations;
        private int lC;
        private int lR;

        LayerRevolver(int layer, int rotations) {
            this.layer = layer;

            lC = columns - layer * 2;
            lR = rows - layer * 2;

            int elementsOnLayer = lR * lC;
            if (elementsOnLayer != 4) {
                int tlc = columns - (layer + 1) * 2;
                int tlr = rows - (layer + 1) * 2;
                elementsOnLayer -= tlc * tlr;
            }
            this.rotations = rotations % elementsOnLayer;

            lC--;
            lR--;
        }

        void revolve() {
            int row = layer, column = layer;

            //revolve by lane a
            for (; column < layer + lC; column++) {
                el = sourceMatrix[column][row];
                rotateByA(column - layer, rotations);
            }

            //revolve by lane b
            for (; row < layer + lR; row++) {
                el = sourceMatrix[column][row];
                rotateByB(row - layer, rotations);
            }

            //revolve by lane c
            for (; column > layer; column--) {
                el = sourceMatrix[column][row];
                rotateByC(lC + layer - column, rotations);
            }

            //revolve by lane d
            for (; row > layer; row--) {
                el = sourceMatrix[column][row];
                rotateByD(lR + layer - row, rotations);
            }
        }

        private void rotateByA(int c, int rotations) {
            if (rotations + c < lC) {
                finalMatrix[layer + c + rotations][layer] = el;
            } else {
                rotations -= lC - c;
                rotateByB(0, rotations);
            }
        }

        private void rotateByB(int r, int rotations) {
            if (rotations + r < lR) {
                finalMatrix[layer + lC][layer + r + rotations] = el;
            } else {
                rotations -= lR - r;
                rotateByC(0, rotations);
            }
        }

        private void rotateByC(int c, int rotations) {
            if (rotations + c < lC) {
                finalMatrix[layer + lC - c - rotations][layer + lR] = el;
            } else {
                rotations -= lC - c;
                rotateByD(0, rotations);
            }
        }

        private void rotateByD(int r, int rotations) {
            if (rotations + r < lR) {
                finalMatrix[layer][layer + lR - r - rotations] = el;
            } else {
                rotations -= lR - r;
                rotateByA(0, rotations);
            }
        }
    }

}
