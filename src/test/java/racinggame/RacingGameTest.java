package racinggame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import racinggame.domain.Cars;
import racinggame.domain.AlwaysMoveStrategy;
import racinggame.domain.FakeRacingGameObserver;
import racinggame.domain.RoundSize;

import static org.assertj.core.api.Assertions.assertThat;

public class RacingGameTest {
    private static final int ROUND_MOVE_LOCATION = 1;

    @CsvSource(
            value = {"pobi,crong,honux=0", "pobi,crong,honux=10", "pobi,crong,honux=100"},
            delimiter = '='
    )
    @DisplayName("요청한 라운드만큼 라운드가 진행 되었나")
    @ParameterizedTest
    void start(String names, int roundSize) {
        FakeRacingGameObserver observer = new FakeRacingGameObserver();

        RacingGame game = new RacingGame(
                Cars.of(names, new AlwaysMoveStrategy(ROUND_MOVE_LOCATION))
        );
        game.registerObserver(observer);

        game.start(new RoundSize(roundSize));
        assertThat(
                observer.matchRoundCounter(roundSize)
        ).isTrue();
    }
}
