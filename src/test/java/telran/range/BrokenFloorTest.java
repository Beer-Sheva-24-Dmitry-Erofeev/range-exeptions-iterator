package telran.range;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BrokenFloorTest {

    private int getMinimalBrokenFloor(BallBrokenFloor bbf) {

        // This is a modified version of the binary search
        int low = 1;
        int high = Integer.MAX_VALUE;
        int mid = low + (high - low) / 2; // Logically the same as "(low + high)/2" but it handles overflow
        int res = high;

        while (low <= high) {
            try {
                bbf.checkFloor(mid);
                low = mid + 1;
            } catch (IllegalArgumentException e) {
                high = mid - 1;
            } catch (Exception e) {
                res = mid;
                high = mid - 1;
            }
            mid = low + (high - low) / 2;
        }
        return res;
    }

    @Test
    void minimalBrokenFloorTest() {
        int[] floors = {17, 69, 200, 1001, 2000, Integer.MAX_VALUE - 1};
        for (int i = 0; i < floors.length; i++) {
            BallBrokenFloor bbf = new BallBrokenFloor(floors[i]);
            assertEquals(bbf.getMinBrokenFloor(), getMinimalBrokenFloor(bbf));
        }
    }
}
