package telran.range;

import java.util.Random;

public class BallBrokenFloor {
    private int nFloors;
    private int minBrokenFloor;

    public BallBrokenFloor(int nFloors) {
        this.nFloors = nFloors;
        minBrokenFloor = new Random().nextInt(1, nFloors + 1);
    }

    public int getMinBrokenFloor() {
        // Theis getter is for testing purposes only
        return minBrokenFloor;
    }

    public int getNFloors() {
        // Я добавил этот геттер, потому что не понимаю, как иначе
        // можно получить общее количество этажей, поскольку nFloors у нас private
        // Это правильно или нет?
        return nFloors;
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
