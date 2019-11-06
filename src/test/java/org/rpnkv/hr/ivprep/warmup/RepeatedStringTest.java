package org.rpnkv.hr.ivprep.warmup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepeatedStringTest {

    @Test
    void repeatedString1() {
        assertEquals(7L, RepeatedString.repeatedString("aba", 10));
    }

    @Test
    void repeatedString2() {
        assertEquals(1000000000000L, RepeatedString.repeatedString("a", 1000000000000L));
    }
}