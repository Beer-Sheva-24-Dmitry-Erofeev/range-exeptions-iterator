package telran.range;

import telran.range.exceptions.OutOfRangeMaxValueException;
import telran.range.exceptions.OutOfRangeMinValueException;

public class Range {

    private static final String ERR_MESS_MAX_LESS_OR_EQUAL_MIN = "Max less or equal Min";

    private int min;
    private int max;

    private Range(int min, int max) {
        this.min = min;
        this.max = max;
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
