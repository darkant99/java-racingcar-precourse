package racinggame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racinggame.exception.InvalidCarLocationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CarLocationTest {
    @ValueSource(ints = {
            0, 10, 100
    })
    @DisplayName("정상 생성 테스트")
    @ParameterizedTest
    public void ofTest(int size) {
        assertDoesNotThrow(() -> CarLocation.of(size));
    }

    @ValueSource(ints = {
            -1, -10, -100
    })
    @DisplayName("음수 유효성 검사 테스트")
    @ParameterizedTest
    public void ofInvalidNegativeTest(int size) {
        assertThatThrownBy(() -> CarLocation.of(size))
                .isInstanceOf(InvalidCarLocationException.class);
    }

    @ValueSource(ints = {
            0, 100, 1000
    })
    @DisplayName("move 테스트")
    @ParameterizedTest
    public void moveTest(int moveSize) {
        CarLocation carLocation = CarLocation.zero();

        assertThat(
                carLocation.move(moveSize)
        ).isEqualTo(
                CarLocation.of(moveSize)
        );
    }

    @ValueSource(ints = {
            -1000, -100, -1
    })
    @DisplayName("move의 결과가 음수라면 생성자와 음수 유효성 검사에 걸린다.")
    @ParameterizedTest
    public void moveInvalidNegativeTest(int moveSize) {
        CarLocation carLocation = CarLocation.zero();

        assertThatThrownBy(() -> carLocation.move(moveSize))
                .isInstanceOf(InvalidCarLocationException.class);
    }

    @ValueSource(ints = {
            0, 1, 100
    })
    @DisplayName("RoundSize 객체는 스레드 안전하다.")
    @ParameterizedTest
    void threadSafetyTest(int testLocation) {

        CarLocation carLocation = CarLocation.of(testLocation);

        carLocation.move(testLocation);

        assertThat(carLocation)
                .isEqualTo(CarLocation.of(testLocation));
    }
}