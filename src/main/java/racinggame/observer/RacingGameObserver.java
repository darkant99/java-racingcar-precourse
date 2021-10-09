package racinggame.observer;

import racinggame.domain.Cars;

public interface RacingGameObserver {
    void gameStart();

    void gameEnd(Cars cars);

    void updateRound(Cars cars);
}
