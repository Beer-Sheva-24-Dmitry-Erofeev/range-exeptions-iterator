package telran.range;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import org.junit.jupiter.api.Test;

import telran.range.exceptions.OutOfRangeMaxValueException;
import telran.range.exceptions.OutOfRangeMinValueException;

public class RangeTest {

    private static final int MIN = 50;
    private static final int MAX = 100;
    Range range = Range.getRange(MIN, MAX);

    @Test
    void wrongRangeCreatingTest() {
        assertThrowsExactly(IllegalArgumentException.class, () -> Range.getRange(200, 100));
    }

    @Test
    void rightNumberTest() throws Exception {
        range.checkNumber(55);
    }

    @Test
    void wrongNumberTest() throws Exception {
        assertThrowsExactly(OutOfRangeMaxValueException.class, () -> range.checkNumber(MAX + 1));
        assertThrowsExactly(OutOfRangeMinValueException.class, () -> range.checkNumber(MIN - 1));
    }

    @Test
    void iteratorTest() {

        // Setup
        Range rangeIt = Range.getRange(0, 11);

        // Predicates
        Predicate<Integer> isEvenNumber = a -> a % 2 == 0;
        Predicate<Integer> isOddNumber = a -> a % 2 != 0;

        // Expected arrays
        Integer[] expectedEvens = {0, 2, 4, 6, 8, 10};
        Integer[] expectedOdds = {1, 3, 5, 7, 9, 11};
        Integer[] expectedNoPredicate = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

        // Test even number predicate
        Iterator<Integer> iteratorEvens = rangeIt.iterator();
        rangeIt.setPredicate(isEvenNumber);
        Integer[] actualEvens = iterateArray(expectedEvens, iteratorEvens);
        assertArrayEquals(expectedEvens, actualEvens);
        assertThrowsExactly(NoSuchElementException.class, iteratorEvens::next);

        // Test odd number predicate
        Iterator<Integer> iteratorOdds = rangeIt.iterator();
        rangeIt.setPredicate(isOddNumber);
        Integer[] actualOdds = iterateArray(expectedOdds, iteratorOdds);
        assertArrayEquals(expectedOdds, actualOdds);
        assertThrowsExactly(NoSuchElementException.class, iteratorOdds::next);

        // Test with no predicate
        Iterator<Integer> iteratorNoPredicate = rangeIt.iterator();
        Integer[] actualNoPredicate = iterateArray(expectedNoPredicate, iteratorNoPredicate);
        assertArrayEquals(expectedNoPredicate, actualNoPredicate);
        assertThrowsExactly(NoSuchElementException.class, iteratorNoPredicate::next);
    }

    private Integer[] iterateArray(Integer[] expectedLength, Iterator<Integer> iterator) {
        Integer[] resArray = new Integer[expectedLength.length];
        int index = 0;
        while (iterator.hasNext()) {
            resArray[index++] = iterator.next();
        }
        return resArray;
    }
}
