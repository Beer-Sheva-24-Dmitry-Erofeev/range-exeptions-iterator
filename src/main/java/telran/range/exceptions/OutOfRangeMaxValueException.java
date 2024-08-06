package telran.range.exceptions;

public class OutOfRangeMaxValueException extends Exception {

    public OutOfRangeMaxValueException(int max, int value) {
        super(String.format("Out of range, max: %d, value: %d", max, value));
    }
}
