package org.rpnkv.hr.ivprep.arrays;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MinimumSwaps2 {

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
        int swaps = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i + 1) {
                swap(i, requiredElementPosition(i + 1, i, arr), arr);
                swaps++;
            }
        }
        return swaps;
    }

    static int requiredElementPosition(int required, int threshold, int[] array) {
        for (int i = threshold; i < array.length; i++) {
            if (array[i] == required) {
                return i;
            }
        }

        throw new NoSuchElementException("NO el fould " + required + " within " + Arrays.toString(array));
    }

    static void swap(int i1, int i2, int[] arr) {
        int buffer = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = buffer;
    }

}
