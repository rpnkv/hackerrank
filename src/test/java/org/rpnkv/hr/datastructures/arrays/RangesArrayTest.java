package org.rpnkv.hr.datastructures.arrays;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

import java.util.NavigableSet;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class RangesArrayTest {

    @Test
    void getAffectedRangesTest() {
        ArrayManipulation.Range zeroRange = new ArrayManipulation.Range(0, 10, 15);

        ArrayManipulation.RangesArray rangesArray = new ArrayManipulation.RangesArray(zeroRange);

        NavigableSet<ArrayManipulation.Range> ranges = getRanges(rangesArray);

        ArrayManipulation.Range expectedRange = new ArrayManipulation.Range(5, 15, 25);
        ranges.add(expectedRange);

        assertEquals(zeroRange, ranges.floor(zeroRange));

        for (int i = 1; i < 5; i++) {
            assertEquals(expectedRange, ranges.floor(expectedRange));
        }
    }

    @Test
    void navigableSetTest() {
        NavigableSet<Integer> set = new TreeSet<>();

        for (int i = 0; i < 5; i++) {
            set.add(i);
        }

        set.remove(1);
        int startKey = set.floor(1);

        SortedSet<Integer> integers = set.subSet(startKey, 3);
        integers.add(1);

       integers.forEach(System.out::println);
    }

    @Test
    void appendRangeWithShiftedBegin() {

    }

    @SuppressWarnings("unchecked")
    private NavigableSet<ArrayManipulation.Range> getRanges(ArrayManipulation.RangesArray rangesArray) {
        Optional<Object> ranges = ReflectionUtils
                .readFieldValue(ArrayManipulation.RangesArray.class, "ranges", rangesArray);

        if (ranges.isPresent()) {
            return (NavigableSet<ArrayManipulation.Range>) ranges.get();
        } else {
            throw new IllegalStateException("No fiend 'ranges' found in 'RangesArray'");
        }
    }
}