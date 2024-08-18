package telran.range;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import telran.range.exceptions.OutOfRangeMaxValueException;
import telran.range.exceptions.OutOfRangeMinValueException;

public class Range implements Iterable<Integer> {

    private static final String ERROR_MESSAGE_MAX_LESS_OR_EQUAL_MIN = "Max less or equal Min";

    private final int min;
    private final int max;
    private Predicate<Integer> predicate;

    private Range(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new RangeIterator();
    }

    // Inner class or nested class - class within a class
    private class RangeIterator implements Iterator<Integer> {

        int current = min;

        public RangeIterator() {
            this.current = findNextValid(min);
        }

        @Override
        // Checks if there are more of the same class objects to iterate
        public boolean hasNext() {
            return current <= max;
        }

        @Override
        public Integer next() {
            // Returns the reference to the next object
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int nextValue = current;
            current = findNextValid(current + 1);
            return nextValue;
        }

        private int findNextValid(int next) {
            if (predicate != null) {
                while (next <= max && !predicate.test(next)) {
                    next++;
                }
            }
            return next;
        }

    }

    public void setPredicate(Predicate<Integer> predicate) {
        this.predicate = predicate;
    }

    public static Range getRange(int min, int max) {
        if (max <= min) {
            throw new IllegalArgumentException(ERROR_MESSAGE_MAX_LESS_OR_EQUAL_MIN);
        }
        return new Range(min, max);
    }

    public void checkNumber(int number) throws OutOfRangeMaxValueException, OutOfRangeMinValueException {
        if (number > max) {
            throw new OutOfRangeMaxValueException(max, number);
        }
        if (number < min) {
            throw new OutOfRangeMinValueException(min, number);
        }
    }

}
