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
            ranges.appendQuery(new Range(query[0], query[1], query[2]));
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

        void appendQuery(Range appendingRange) {
            Set<Integer> affectedRanges = getAffectedRanges(appendingRange);

            for (Integer affectedRangeBegin : affectedRanges) {
                Collection<Integer> appendedRanges = appendRange(ranges.get(affectedRangeBegin), appendingRange);
                affectedRanges.addAll(appendedRanges);
            }
        }

        private Set<Integer> getAffectedRanges(Range appendingRange) {
            Integer rangeStartKey = ranges.floorKey(appendingRange.begin);
            return ranges.subMap(rangeStartKey, appendingRange.end).keySet();
        }

        private Collection<Integer> appendRange(Range affectedRange, Range appendingRange) {
            if (appendingRange.begin > affectedRange.begin) {
                return cutRangeBegin(affectedRange, appendingRange);
            }

            if (appendingRange.end < affectedRange.end) {

            }


            return Collections.emptyList();
        }

        private Collection<Integer> cutRangeBegin(Range affectedRange, Range appendingRange) {
            Range range = new Range(appendingRange.begin, affectedRange.end,
                    appendingRange.value + affectedRange.value);

            affectedRange.end = appendingRange.begin - 1;
            ranges.put(range.begin, range);
            return Collections.singleton(range.begin);
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
