package org.rpnkv.hr.ivprep.warmup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JumpingOnCloudsTest {

    @Test
    void jumpingOnClouds1() {
        int[] c = new int[]{0, 0, 1, 0, 0, 1, 0};
        assertEquals(3, JumpingOnClouds.jumpingOnClouds(c));
    }

    @Test
    void jumpingOnClouds2() {
        int[] c = new int[]{0, 0, 0, 0, 1, 0};
        assertEquals(3, JumpingOnClouds.jumpingOnClouds(c));
    }

    @Test
    void jumpingOnClouds3() {
        int[] c = new int[]{0, 0, 0, 1, 0, 0};
        assertEquals(3, JumpingOnClouds.jumpingOnClouds(c));
    }

    @Test
    void jumpingOnClouds4() {
        int[] c = new int[]{0, 0, 1, 0, 0, 1, 0};
        assertEquals(4, JumpingOnClouds.jumpingOnClouds(c));
    }
}