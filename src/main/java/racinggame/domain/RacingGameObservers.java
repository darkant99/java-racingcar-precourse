package racinggame.domain;

import java.util.ArrayList;
import java.util.List;

public class RacingGameObservers {
    private final List<RacingGameObserver> observers;

    public RacingGameObservers() {
        this.observers = new ArrayList<>();
    }

    public void register(RacingGameObserver observer) {
        this.observers.add(observer);
    }

    public void updateRound(Cars cars) {
        for (RacingGameObserver iObserver : observers) {
            iObserver.updateRound(cars);
        }
    }
}
