package racinggame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class CarTest {
    @ValueSource(ints = {
            10, 100, 1000
    })
    @DisplayName("Car Move 테스트")
    @ParameterizedTest
    void moveTest(int moveLocation) {
        Car car = new Car(
                new CarName("테스트카"),
                new FakeMoveStrategy(moveLocation)
        );

        assertThat(car.move())
                .isEqualTo(CarLocation.of(moveLocation));
    }
}