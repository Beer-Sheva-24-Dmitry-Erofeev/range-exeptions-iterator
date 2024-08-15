package telran.range;

import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.range.exceptions.OutOfRangeMaxValueException;
import telran.range.exceptions.OutOfRangeMinValueException;

public class Range implements Iterable<Integer> {

    private static final String ERR_MESS_MAX_LESS_OR_EQUAL_MIN = "Max less or equal Min";

    private final int min;
    private final int max;

    private Range(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new RangeIterator();
    }

    private class RangeIterator implements Iterator<Integer> {

        int current = min;

        @Override
        public boolean hasNext() {
            // It just checks if threre are more of the same calss objects to iterate
            return current <= max;
        }

        @Override
        public Integer next() {
            // Returns the reference to the next object
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return current++;
        }
        
    }

    public static Range getRange(int min, int max) {
        if (max <= min) {
            throw new IllegalArgumentException(ERR_MESS_MAX_LESS_OR_EQUAL_MIN);
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
