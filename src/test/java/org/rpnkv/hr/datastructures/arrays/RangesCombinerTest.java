package org.rpnkv.hr.datastructures.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RangesCombinerTest {

    private int bound = 7;

    /**
     * Verifies, that processing range is splitted for 2 ranges as appending range begins after processing.
     * Here processing range starts at 5 end at 10, appending range starts at 7. Processing range must be shorted to
     * end at 6, and new range must start at 7 and end at 10 (older processing range beginning).
     * Splitting into 2 ranges is preformed by Range.splitByBeginning(int newEnd).
     */
    @Test
    void cutBeginWhenRequiredTest() {
        ArrayManipulation.Range processingRange = mock(ArrayManipulation.Range.class, "pr range"),
                derivedRange = mock(ArrayManipulation.Range.class, "cut a range");

        when(processingRange.startsBefore(bound)).thenReturn(true);
        when(processingRange.splitByBeginning(bound)).thenReturn(derivedRange);

        ArrayManipulation.Range finalRange =
                ArrayManipulation.RangesCombiner.cutBeginningIfRequired(processingRange, bound);

        assertEquals(finalRange, derivedRange);
        verify(processingRange).startsBefore(bound);
    }

    /**
     * Verifies that processing range is checked to not begin before appending range, so no changes to ranges made.
     */
    @Test
    void cutBeginWhenNotRequiredTest() {
        ArrayManipulation.Range processingRange = mock(ArrayManipulation.Range.class, "pr range");

        assertEquals(processingRange, ArrayManipulation.RangesCombiner
                .cutBeginningIfRequired(processingRange, bound));

        verify(processingRange).startsBefore(bound);
    }

    /**
     *
     */
    @Test
    void cutEndWhenRequiredTest() {
        ArrayManipulation.Range processingRange = mock(ArrayManipulation.Range.class, "processing range"),
                derivedRange = mock(ArrayManipulation.Range.class, "derived range");

        when(processingRange.endsBefore(bound)).thenReturn(true);
        when(processingRange.splitByEnding(bound)).thenReturn(derivedRange);

        assertEquals(derivedRange, ArrayManipulation.RangesCombiner.cutEndIfRequired(processingRange, bound));
        verify(processingRange).endsBefore(bound);
    }

    @Test
    void cutEndWhenNotRequiredTest() {
        ArrayManipulation.Range processingRange = mock(ArrayManipulation.Range.class);

        assertEquals(processingRange, ArrayManipulation.RangesCombiner.cutEndIfRequired(processingRange, bound));
        verify(processingRange).endsBefore(bound);
    }
}