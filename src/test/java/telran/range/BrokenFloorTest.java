package telran.range;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BrokenFloorTest {

    private int getMinimalBrokenFloor(BallBrokenFloor bbf) {

        // This is a modified version of the binary search
        // I don't know how to apply the standart one or is it even possible
        int low = 1;
        int high = bbf.getNFloors();
        int mid = (low + high) / 2;
        int res = high;

        while (low <= high) {
            try {
                bbf.checkFloor(mid);
                low = mid + 1;
            } catch (Exception e) {
                res = mid;
                high = mid - 1;
            }
            mid = (low + high) / 2;
        }
        return res;
    }

    @Test
    void minimalBrokenFloorTest() {
        int[] floors = {200, 17, 69, 1001, 2000, 696969};
        for (int i = 0; i < floors.length; i++) {
            BallBrokenFloor bbf = new BallBrokenFloor(floors[i]);
            assertEquals(bbf.getMinBrokenFloor(), getMinimalBrokenFloor(bbf));
        }
    }
}
