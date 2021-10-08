package racinggame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import racinggame.ThreadTestUtils;
import racinggame.exception.InvalidRoundSizeException;

import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RoundSizeTest {
    private static final int THREAD_SAFETY_TEST_SIZE = 50000;

    @ValueSource(ints = {
            0, 10, 100
    })
    @DisplayName("정상 생성 테스트")
    @ParameterizedTest
    public void ctorTest(int size) {
        assertDoesNotThrow(() -> new RoundSize(size));
    }

    @ValueSource(ints = {
            -1, -10, -100
    })
    @DisplayName("음수 유효성 검사 테스트")
    @ParameterizedTest
    public void ctorInvalidNegativeTest(int size) {
        assertThatThrownBy(() -> new RoundSize(size))
                .isInstanceOf(InvalidRoundSizeException.class);
    }

    @CsvSource({
            "0,0,true",
            "0,1,true",
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

    @DisplayName("RoundSize 객체는 스레드 안전하지 못한다.")
    @Test
    void notThreadSafetyTest() throws InterruptedException {
        RoundSize roundSizeThis = new RoundSize(THREAD_SAFETY_TEST_SIZE);
        ThreadTestUtils.run(roundSizeThis::doneRound, THREAD_SAFETY_TEST_SIZE);

        RoundSize roundSizeThat = new RoundSize(THREAD_SAFETY_TEST_SIZE);
        ThreadTestUtils.run(roundSizeThat::doneRound, THREAD_SAFETY_TEST_SIZE);

        assertThat(roundSizeThis)
                .isNotEqualTo(roundSizeThat);
    }
}