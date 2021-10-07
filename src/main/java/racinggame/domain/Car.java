package racinggame.domain;

public class Car {
    private final MoveStrategy moveStrategy;
    private final CarName name;

    private CarLocation location;

    public Car(final CarName name, final MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
        this.name = name;

        this.location = CarLocation.zero();
    }

    public CarLocation move() {
        location = moveStrategy.move(location);
        return location;
    }
}
