package racinggame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import racinggame.ThreadTestUtils;

import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

class RoundSizeTest {
    private static final int THREAD_SAFETY_TEST_SIZE = 50000;

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