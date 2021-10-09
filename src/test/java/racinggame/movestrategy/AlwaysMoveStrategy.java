package racinggame.movestrategy;

import racinggame.domain.CarLocation;

public class AlwaysMoveStrategy implements MoveStrategy {
    private final int moveLocation;

    public AlwaysMoveStrategy(final int moveLocation) {
        this.moveLocation = moveLocation;
    }

    @Override
    public CarLocation move(final CarLocation currentLocation) {
        return currentLocation.move(moveLocation);
    }
}
