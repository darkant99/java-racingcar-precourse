package racinggame.domain;

// 짝수 순번의 자동차만 움직일 수 있는 이동 전략
public class UnfairEvenCarMoveStrategy implements MoveStrategy {
    private final int moveLocation;

    private int indexCounter;

    public UnfairEvenCarMoveStrategy(int moveLocation) {
        this.moveLocation = moveLocation;

        this.indexCounter = 0;
    }

    @Override
    public CarLocation move(final CarLocation currentLocation) {
        if (isMovable()) {
            return currentLocation.move(moveLocation);
        }
        return currentLocation;
    }

    private boolean isMovable() {
        return indexCounter++ % 2 == 0;
    }
}
