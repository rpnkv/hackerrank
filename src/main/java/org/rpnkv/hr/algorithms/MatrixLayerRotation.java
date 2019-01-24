package org.rpnkv.hr.algorithms;

import java.util.List;

/**
 * https://www.hackerrank.com/challenges/matrix-rotation-algo/problem
 */
public class MatrixLayerRotation {

    private int r, columns, rows, el;
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
        for (int i = 0; i < lessDimension / 2; i++) {
            LayerRevolver layerRevolver = new LayerRevolver(i, r);
            layerRevolver.revolve();
        }
    }

    class LayerRevolver {
        private final int layer, rotations, lC, lR;

        LayerRevolver(int layer, int rotations) {
            this.layer = layer;

            lC = columns - layer * 2;
            lR = rows - layer * 2;

            this.rotations = rotations % (lR + lC);
        }

        void revolve() {
            int r = layer, c;

            //revolve lane a
            for (int i = layer + 1; i < layer + lC; i++) {
                el = sourceMatrix[i][r];
                rotateByA(i, rotations);
            }

            //revolve lane b
            c = layer + lC - 1;
            for (int i = layer + 1; i < layer + lR; i++) {
                el = sourceMatrix[c][i];
                rotateByB(i, rotations);
            }

            //revolve lane c
            r = layer + lR - 1;
            for (int i = layer + lR - 2; i >= layer; i--) {
                el = sourceMatrix[i][r];
                rotateByC(i, rotations);
            }

            //revolve lane d
            c = layer;
            for (int i = layer + lR - 2; i >= layer; i--) {
                el = sourceMatrix[c][i];
                rotateByD(i, rotations);
            }
        }

        private void rotateByA(int c, int rotations) {
            int cF = layer + lC - 1;
            if (c + rotations > cF) {
                rotations = rotations - (cF - c) - 1;
                rotateByB(layer + 1, rotations);
            } else {
                cF = c + rotations;
                finalMatrix[cF][layer] = el;
            }
        }

        private void rotateByB(int r, int rotations) {
            int rF = layer + lR - 1;
            if (r + rotations > rF) {
                rotations = rotations - (rF - r) - 1;
                rotateByC(layer + lC - 2, rotations);
            } else {
                rF = r + rotations;
                finalMatrix[layer + lC - 1][rF] = el;
            }
        }

        private void rotateByC(int c, int rotations) {
            int cF = layer;
            if (c - rotations < cF) {
                rotations = rotations - (c + 1);
                rotateByD(layer + lR - 2, rotations);
            } else {
                cF = c - rotations;
                finalMatrix[cF][layer + lR - 1] = el;
            }
        }

        private void rotateByD(int r, int rotations) {
            int rF = layer;
            if (r - rotations < layer) {
                rotations -= r + 1;
                rotateByA(layer + 1, rotations);
            } else {
                rF = r - rotations;
                finalMatrix[layer][rF] = el;
            }
        }
    }

}
