package racinggame.domain;

@FunctionalInterface
public interface MoveStrategy {
    CarLocation move(CarLocation currentLocation);
}
