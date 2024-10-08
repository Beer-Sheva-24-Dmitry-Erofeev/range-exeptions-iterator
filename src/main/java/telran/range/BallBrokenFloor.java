package telran.range;

import java.util.Random;

public class BallBrokenFloor {

    private final int nFloors;
    private final int minBrokenFloor;

    public BallBrokenFloor(int nFloors) {
        this.nFloors = nFloors;
        minBrokenFloor = new Random().nextInt(1, nFloors + 1);
    }

    public int getMinBrokenFloor() {
        // This getter is for testing purposes only
        return minBrokenFloor;
    }

    public void checkFloor(int floor) throws Exception {
        if (floor > nFloors) {
            throw new IllegalArgumentException();
        }
        if (floor >= minBrokenFloor) {
            throw new Exception("Ball is broken");
        }
    }
}
