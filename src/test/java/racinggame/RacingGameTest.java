package racinggame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import racinggame.domain.*;
import racinggame.movestrategy.AlwaysMoveStrategy;
import racinggame.observer.FakeRacingGameObserver;
import racinggame.observer.RacingGameObserver;

import static org.assertj.core.api.Assertions.assertThat;

public class RacingGameTest {
    private static final int ROUND_MOVE_LOCATION = 1;

    private void gameStart(String names, int roundSize, RacingGameObserver observer) {
        RacingGame game = new RacingGame(
                Cars.of(names, new AlwaysMoveStrategy(ROUND_MOVE_LOCATION))
        );
        game.registerObserver(observer);

        game.start(new RoundSize(roundSize));
    }

    @CsvSource(
            value = {"pobi,crong,honux=1", "pobi,crong,honux=10", "pobi,crong,honux=100"},
            delimiter = '='
    )
    @DisplayName("요청한 라운드만큼 라운드가 진행 되었나")
    @ParameterizedTest
    void roundTest(String names, int roundSize) {
        FakeRacingGameObserver observer = new FakeRacingGameObserver();

        gameStart(names, roundSize, observer);

        assertThat(
                observer.matchRoundCounter(roundSize)
        ).isTrue();
    }

    @DisplayName("게임이 시작 되었나")
    @Test
    void gameStartTest() {
        FakeRacingGameObserver observer = new FakeRacingGameObserver();

        gameStart("테스트카1,테스트카2", 1, observer);
        assertThat(
                observer.isGameStart()
        ).isTrue();
    }

    @DisplayName("게임이 종료 되었나")
    @Test
    void gameEndTest() {
        FakeRacingGameObserver observer = new FakeRacingGameObserver();

        gameStart("테스트카1,테스트카2", 1, observer);
        assertThat(
                observer.isGameEnd()
        ).isTrue();
    }
}
