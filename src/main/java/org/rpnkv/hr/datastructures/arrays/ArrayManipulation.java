package org.rpnkv.hr.datastructures.arrays;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

/**
 * https://www.hackerrank.com/challenges/crush/problem
 */
public class ArrayManipulation {

    private long[] values;

    long arrayManipulation(int n, int[][] queries) {
        values = new long[n];
        MaxRanges ranges = new MaxRanges(0, new Range(0, n, 0));

        for (int i = 0; i < queries.length; i++) {
            Range range = new Range(queries[i][0] - 1, queries[i][1], queries[i][2]);
            ranges.append(range);
        }

        return ranges.currentMaxValue;
    }

    class MaxRanges {

        private long currentMaxValue;
        private Collection<Range> ranges;

        public MaxRanges(long currentMaxValue, Range firstRange) {
            this.currentMaxValue = currentMaxValue;
            ranges = new LinkedList<>();
        }

        public void append(Range appendingRange) {
            Range currentRange = null;//TODO find out why variable isn't initialized until assigned null

            for (int i = appendingRange.begin; i < appendingRange.end; i++) {
                values[i] += appendingRange.value;
                if (values[i] > currentMaxValue) {
                    ranges = new LinkedList<>();
                    currentRange = new Range(i, i, values[i]);
                    currentMaxValue = values[i];
                    continue;
                }
                if (values[i] == currentMaxValue) {
                    if (currentRange == null) {
                        currentRange = new Range(i, i, values[i]);
                    }
                    currentRange.end = i;
                    continue;
                }
                if (values[i] < currentMaxValue) {
                    if(currentRange != null){
                        ranges.add(currentRange);
                        currentRange = null;
                    }
                }
            }
            if(currentRange != null){
                ranges.add(currentRange);
            }
        }
    }

    class Range {
        private final long value;

        private final int begin;
        private int end;

        public Range(int begin, int end, long value) {

            this.begin = begin;
            this.end = end;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Range range = (Range) o;
            return value == range.value &&
                    begin == range.begin &&
                    end == range.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, begin, end);
        }
    }

}
