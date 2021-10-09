package racinggame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import racinggame.exception.InvalidRoundSizeException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RoundSizeTest {
    @ValueSource(ints = {
            1, 10, 100
    })
    @DisplayName("정상 생성 테스트")
    @ParameterizedTest
    public void ctorTest(int size) {
        assertDoesNotThrow(() -> new RoundSize(size));
    }

    @ValueSource(ints = {
            0, -1, -10, -100
    })
    @DisplayName("제로, 음수 유효성 검사 테스트")
    @ParameterizedTest
    public void ctorInvalidNegativeTest(int size) {
        assertThatThrownBy(() -> new RoundSize(size))
                .isInstanceOf(InvalidRoundSizeException.class);
    }

    @CsvSource({
            "1,1,true",
            "2,1,false",
            "10,5,false"
    })
    @DisplayName("doneRound 호출시 남은 라운드 값을 1회 차감한다.")
    @ParameterizedTest
    void doneRoundAndIsCompleted(int size, int doneSize, boolean expert) {
        RoundSize roundSize = new RoundSize(size);
        for (int i = 0; i < doneSize; i++) {
            roundSize.doneRound();
        }

        assertThat(roundSize.isCompleted())
                .isEqualTo(expert);
    }

    @ValueSource(ints = {
            1, 10, 100
    })
    @DisplayName("RoundSize 객체는 스레드 안전하지 못한다.")
    @ParameterizedTest
    void notThreadSafetyTest(int testSize) {
        RoundSize roundSizeThis = new RoundSize(testSize);

        roundSizeThis.doneRound();

        assertThat(roundSizeThis)
                .isNotEqualTo(new RoundSize(testSize));
    }
}