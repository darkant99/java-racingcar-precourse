package racinggame.domain;

public class FakeRacingGameObserver implements RacingGameObserver {
    private int roundCounter;
    private boolean gameStart;
    private boolean gameEnd;

    public FakeRacingGameObserver() {
        this.roundCounter = 0;
        this.gameStart = false;
        this.gameEnd = false;
    }

    public boolean matchRoundCounter(int roundCounter) {
        return this.roundCounter == roundCounter;
    }

    @Override
    public void gameStart() {
        gameStart = true;
    }

    @Override
    public void gameEnd(final Cars cars) {
        gameEnd = true;
    }

    @Override
    public void updateRound(final Cars cars) {
        ++roundCounter;
    }

    public boolean isGameStart() {
        return gameStart;
    }

    public boolean isGameEnd() {
        return gameEnd;
    }
}
