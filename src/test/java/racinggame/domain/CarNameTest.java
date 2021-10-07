package racinggame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racinggame.exception.InvalidCarNameException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CarNameTest {
    @ValueSource(strings = {
            "가",
            "가나다라마"
    })
    @DisplayName("인스턴스 정상 생성 테스트")
    @ParameterizedTest
    void ctorTest(String nameValue) {
        assertDoesNotThrow(() -> new CarName(nameValue));
    }

    @ValueSource(strings = {
            "",
            "가나다라마바"
    })
    @DisplayName("자동차 이름은 공백이거나 5자를 초과할 수 없다.")
    @ParameterizedTest
    void ctorInvalidCarNameExceptionTest(String nameValue) {
        assertThatThrownBy(() -> new CarName(nameValue))
                .isInstanceOf(InvalidCarNameException.class);
    }
}
