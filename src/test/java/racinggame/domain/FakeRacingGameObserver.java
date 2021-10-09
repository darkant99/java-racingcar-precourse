package racinggame.domain;

public class FakeRacingGameObserver implements RacingGameObserver {
    private int roundCounter;

    public FakeRacingGameObserver() {
        this.roundCounter = 0;
    }

    public boolean matchRoundCounter(int roundCounter) {
        return this.roundCounter == roundCounter;
    }

    @Override
    public void gameStart() {
        roundCounter = 0;
    }

    @Override
    public void updateRound(final Cars cars) {
        ++roundCounter;
    }
}
