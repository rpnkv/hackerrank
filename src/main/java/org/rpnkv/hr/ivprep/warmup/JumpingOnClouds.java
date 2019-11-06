package org.rpnkv.hr.ivprep.warmup;

class JumpingOnClouds {

    // Complete the jumpingOnClouds function below.
    static int jumpingOnClouds(int[] c) {
        return jump(c, 0, 0);
    }

    private static int jump(int[] c, int currentIndex, int jumps) {
        if (currentIndex == c.length - 1) {
            return jumps;
        }

        int j1;
        if (currentIndex + 1 < c.length && c[currentIndex + 1] != 1) {
            j1 = jump(c, currentIndex + 1, jumps + 1);
        } else {
            j1 = Integer.MAX_VALUE;
        }

        int j2;
        if (currentIndex + 2 < c.length && c[currentIndex + 2] != 1) {
            j2 = jump(c, currentIndex + 2, jumps + 1);
        } else {
            j2 = Integer.MAX_VALUE;
        }

        return Math.min(j1, j2);
    }
}

