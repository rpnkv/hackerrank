package org.rpnkv.hr.ivprep.alg;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ArrayRotation {

    // Complete the rotLeft function below.
    static int[] rotLeft(int[] a, int d) {
        final int rotations = d % a.length;
        int[] rotatedArray = new int[a.length];
        for (int i = 1; i <= a.length; i++) {
            int i1;
            if (rotations >= i) {
                int rotations1 = rotations - i;
                i1 = a.length - rotations1;
            } else {
                i1 = i - rotations;
            }

            rotatedArray[i1 - 1] = i - 1;
        }
        for (int i = 0; i < rotatedArray.length; i++) {
            rotatedArray[i] = a[rotatedArray[i]];
        }

        return rotatedArray;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        int[] result = rotLeft(a, d);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
        scanner.close();
    }

}
