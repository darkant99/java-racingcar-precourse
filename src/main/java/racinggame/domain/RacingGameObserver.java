package racinggame.domain;

public interface RacingGameObserver {
    void gameStart();

    void gameEnd(Cars cars);

    void updateRound(Cars cars);
}
