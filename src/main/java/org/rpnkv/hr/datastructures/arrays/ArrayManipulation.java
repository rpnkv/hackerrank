package org.rpnkv.hr.datastructures.arrays;

import org.apache.commons.lang3.NotImplementedException;

import java.util.*;

/**
 * https://www.hackerrank.com/challenges/crush/problem
 */
public class ArrayManipulation {

    long arrayManipulation(int n, int[][] queries) {
        RangesArray ranges = new RangesArray(new Range(0, n, 0));

        for (int[] query : queries) {
            query[0] -= 1;
            query[1] -= 1;
            ranges.appendQuery(query);
        }

        return ranges.maxValue;
    }

    static class RangesArray {

        private long maxValue;
        private NavigableMap<Integer, Range> ranges;

        RangesArray(Range range) {
            ranges = new TreeMap<>();
            ranges.put(range.begin, range);

            maxValue = range.value;
        }

        void appendQuery(int[] query) {
            Iterator<Integer> affectedRanges = getAffectedRanges(query[0], query[1]);

            do {
                Range affectedRange = ranges.get(affectedRanges.next());
                Range cutBeginRange = RangesCombiner.cutBeginningIfRequired(affectedRange, query[0]);
                boolean checkWithPreviousRange = false;
                if (!cutBeginRange.equals(affectedRange)) {
                    putToRanges(cutBeginRange);
                } else {
                    checkWithPreviousRange = true;
                }

                Range cutEndRange = RangesCombiner.cutEndIfRequired(affectedRange, query[1]);
                if (!cutEndRange.equals(affectedRange)) {
                    putToRanges(cutEndRange);
                }

                affectedRange.value += query[3];
                if (affectedRange.value > maxValue) {
                    maxValue = affectedRange.value;
                }

                if (checkWithPreviousRange) {
                    int previousRangeIndex = getPreviousRangeIndex(affectedRange);
                    Range previousRange = ranges.get(previousRangeIndex);
                    if(previousRange.value == affectedRange.value){
                        assert(previousRange.end + 1 == affectedRange.begin);
                        previousRange.end = affectedRange.end;
                        ranges.remove(affectedRange.begin);
                    }
                }

            } while (affectedRanges.hasNext());
        }

        private Iterator<Integer> getAffectedRanges(int begin, int end) {
            Integer rangeStartKey = ranges.floorKey(begin);
            return ranges.subMap(rangeStartKey, end).keySet().iterator();
        }

        private void putToRanges(Range range) {
            ranges.put(range.begin, range);
        }

        private int getPreviousRangeIndex(Range range) {
            return ranges.floorKey(range.begin - 1);
        }
    }

    static class RangesCombiner {

        /**
         * Cuts processing range by the beginning of added range if the beginning of added range is behind the beginning
         * if processed range. Processing range end begins at added range start - 1.
         *
         * @param processingRange
         * @param bound
         * @return range to append added range value.
         */
        static Range cutBeginningIfRequired(Range processingRange, int bound) {
            if (processingRange.startsBefore(bound)) {
                return processingRange.splitByBeginning(bound);
            } else {
                return processingRange;
            }
        }

        /**
         * When add range ends before processing end range - processing range must be split to 2 separate ranges. Processing
         * range must end at the same place that appending range ends. Derived range must start right next to processing
         * range's new end and end at processing range's old end.
         *
         * @param processingRange
         * @param bound
         * @return
         */
        static Range cutEndIfRequired(Range processingRange, int bound) {
            if (processingRange.endsBefore(bound)) {
                return processingRange.splitByEnding(bound);
            } else {
                return processingRange;
            }
        }
    }

    static class Range implements Comparable<Range> {
        private long value;

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

        @Override
        public int compareTo(Range o) {
            return this.begin - o.begin;
        }

        boolean startsBefore(int begin) {
            throw new NotImplementedException("");
        }

        Range splitByBeginning(int newBeginning) {
            throw new NotImplementedException("");
        }

        boolean endsBefore(int end) {
            throw new NotImplementedException("");
        }

        Range splitByEnding(int newEnding) {
            throw new NotImplementedException("");
        }
    }

}
