package org.rpnkv.hr.ivprep.alg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayRotationTest {

    private final int[] source = new int[]{1, 2, 3, 4, 5};

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