package racinggame.movestrategy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racinggame.domain.CarLocation;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomMoveStrategyTest {
    private static final int TEST_SIZE = 1000;

    private static MoveStrategy moveStrategy;

    @BeforeAll
    static void setUp() {
        moveStrategy = new RandomMoveStrategy();
    }

    CarLocation movedLocation() {
        CarLocation location = CarLocation.zero();
        for (int i = 0; i < TEST_SIZE; i++) {
            location = moveStrategy.move(location);
        }
        return location;
    }

    @DisplayName("4/9 확률로 한칸 전진하기 때문에 2번의 1000번 move 요청시 결과 Location 값은 서로 달라야 한다.")
    @Test
    void moveTest() {
        assertThat(movedLocation())
                .isNotEqualTo(movedLocation());
    }
}
