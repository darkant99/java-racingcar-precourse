package racinggame.domain;

public class FakeMoveStrategy implements MoveStrategy  {
    private final int moveLocation;

    public FakeMoveStrategy(final int moveLocation) {
        this.moveLocation = moveLocation;
    }

    @Override
    public CarLocation move(final CarLocation currentLocation) {
        return currentLocation.move(moveLocation);
    }
}
