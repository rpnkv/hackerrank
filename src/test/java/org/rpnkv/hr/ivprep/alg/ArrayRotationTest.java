package org.rpnkv.hr.ivprep.alg;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ArrayRotationTest {

    private final int[] source = new int[]{1, 2, 3, 4, 5};

    @Test
    void minorRotate() {
        int[] rotatedArray = ArrayRotation.rotLeft(new int[]{1, 2, 3}, 2);
        System.out.println(Arrays.toString(rotatedArray));
        assertArrayEquals(rotatedArray, new int[]{3, 1, 2});
    }

    @Test
    void testNoRotate() {
        assertArrayEquals(new int[]{3, 4, 5, 1, 2}, ArrayRotation.rotLeft(source, 2));
    }

    @Test
    void testWith1Rotation() {
        assertArrayEquals(new int[]{2, 3, 4, 5, 1}, ArrayRotation.rotLeft(source, 6));
    }

    @Test
    void testWith2Rotations() {
        assertArrayEquals(new int[]{2, 3, 4, 5, 1}, ArrayRotation.rotLeft(source, 11));
    }
}