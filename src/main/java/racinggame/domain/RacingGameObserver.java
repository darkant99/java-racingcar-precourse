package racinggame.domain;

public interface RacingGameObserver {
    void gameStart();

    void updateRound(Cars cars);
}
