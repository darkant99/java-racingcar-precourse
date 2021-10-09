package racinggame.domain;

import java.util.Objects;

public class Car {
    private final MoveStrategy moveStrategy;
    private final CarName name;

    private CarLocation location;

    public Car(final CarName name, final MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
        this.name = name;

        this.location = CarLocation.zero();
    }

    public CarName name() {
        return name;
    }

    public CarLocation location() {
        return location;
    }

    public CarLocation move() {
        location = moveStrategy.move(location);
        return location;
    }

    public boolean matchLocation(CarLocation location) {
        return this.location.equals(location);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Car car = (Car) o;
        return Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
