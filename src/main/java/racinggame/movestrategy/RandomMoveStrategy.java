package racinggame.movestrategy;

import nextstep.utils.Randoms;
import racinggame.domain.CarLocation;

public class RandomMoveStrategy implements MoveStrategy {
    private static final int MOVE_LOCATION_VALUE = 1;

    private static final int RANDOM_MIN_NUMBER_SIZE = 0;
    private static final int RANDOM_MAX_NUMBER = 9;
    private static final int MOVABLE_NUMBER = 4;

    @Override
    public CarLocation move(final CarLocation currentLocation) {
        if (isMovable()) {
            return currentLocation.move(MOVE_LOCATION_VALUE);
        }
        return currentLocation;
    }

    private boolean isMovable() {
        return Randoms.pickNumberInRange(RANDOM_MIN_NUMBER_SIZE, RANDOM_MAX_NUMBER) >= MOVABLE_NUMBER;
    }
}
