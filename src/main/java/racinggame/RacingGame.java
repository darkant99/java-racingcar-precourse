package racinggame;

import racinggame.domain.Cars;
import racinggame.domain.RacingGameObserver;
import racinggame.domain.RacingGameObservers;

import java.util.ArrayList;
import java.util.List;

public class RacingGame {
    private final Cars cars;
    private final RacingGameObservers observers;

    public RacingGame(final Cars cars) {
        this.cars = cars;
        this.observers = new RacingGameObservers();
    }

    public void registerObserver(final RacingGameObserver observer) {
        observers.register(observer);
    }

    public void start(final int roundSize) {
        for (int roundCounter = 0; roundCounter < roundSize; roundCounter++) {
            runningRound();
        }
    }

    private void runningRound() {
        cars.move();
        observers.updateRound(cars);
    }
}
