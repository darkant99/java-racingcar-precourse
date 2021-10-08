package racinggame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
        assertDoesNotThrow(() -> Cars.of(names, new FakeMoveStrategy(0)));
    }

    @ValueSource(strings = {
            "pobi,pobi,honux",
            "pobi,honux,honux",
            "a,a,b"
    })
    @DisplayName("of 테스트 - 각 자동차의 이름은 중복되지 않아야 한다.")
    @ParameterizedTest
    void ofInvalidCarNameExceptionTest(String names) {
        assertThatThrownBy(() -> Cars.of(names, new FakeMoveStrategy(0)))
                .isInstanceOf(InvalidCarNameException.class);
    }
}
