package racinggame.movestrategy;

import racinggame.domain.CarLocation;

@FunctionalInterface
public interface MoveStrategy {
    CarLocation move(CarLocation currentLocation);
}
