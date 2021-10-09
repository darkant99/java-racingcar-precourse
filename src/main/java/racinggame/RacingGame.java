package racinggame;

import racinggame.domain.Cars;
import racinggame.domain.RacingGameObserver;
import racinggame.domain.RacingGameObservers;
import racinggame.domain.RoundSize;

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

    public void start(final RoundSize roundSize) {
        observers.gameStart();

        while (!roundSize.isCompleted()) {
            runningRound();
            roundSize.doneRound();
        }

        observers.gameEnd(cars);
    }

    private void runningRound() {
        cars.move();
        observers.updateRound(cars);
    }
}
