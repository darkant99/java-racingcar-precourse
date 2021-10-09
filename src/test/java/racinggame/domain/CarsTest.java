package racinggame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import racinggame.movestrategy.AlwaysMoveStrategy;
import racinggame.movestrategy.UnfairEvenCarMoveStrategy;
import racinggame.exception.InvalidCarNameException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CarsTest {
    @ValueSource(strings = {
            "pobi,crong,honux",
            "p,c,h"
    })
    @DisplayName("of 테스트 - 쉼표(,)로 구분되는 자동차 이름과 MoveStrategy를 Cars 객체로 변환한다.")
    @ParameterizedTest
    void ofTest(String names) {
        assertDoesNotThrow(() -> Cars.of(names, new AlwaysMoveStrategy(0)));
    }

    @ValueSource(strings = {
            "pobi,pobi,honux",
            "pobi,honux,honux",
            "a,a,b"
    })
    @DisplayName("of 테스트 - 각 자동차의 이름은 중복되지 않아야 한다.")
    @ParameterizedTest
    void ofInvalidCarNameExceptionTest(String names) {
        assertThatThrownBy(() -> Cars.of(names, new AlwaysMoveStrategy(0)))
                .isInstanceOf(InvalidCarNameException.class);
    }

    @ValueSource(strings = {
            "pobi",
            "pobi,honux",
            "pobi,crong,honux"
    })
    @ParameterizedTest
    void moveTest(String names) {
        Cars cars = Cars.of(names, new AlwaysMoveStrategy(1));
        cars.move();

        for (Car iCar : cars) {
            assertThat(
                    iCar.compareLocation(CarLocation.of(1))
            ).isEqualTo(0);
        }
    }

    @CsvSource(value = {
            "pobi,honux",
            "pobi,crong,honux",
            "pobi,crong,honux,jaewo",
    }, delimiter = '=')
    @ParameterizedTest
    void winnersTest(String names) {
        Cars cars = Cars.of(names, new UnfairEvenCarMoveStrategy(1));
        cars.move();

        int evenCarSize = cars.size() / 2 + cars.size() % 2;
        assertThat(
                cars.winners().size()
        ).isEqualTo(evenCarSize);
    }
}
