package racinggame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racinggame.movestrategy.AlwaysMoveStrategy;

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
                new AlwaysMoveStrategy(moveLocation)
        );

        assertThat(car.move())
                .isEqualTo(CarLocation.of(moveLocation));
    }

    @ValueSource(strings = {
            "테스트카", "pobi", "honux"
    })
    @DisplayName("Car Dto 생성 테스트")
    @ParameterizedTest
    void toDtoNameTest(String carName) {
        Car car = new Car(
                new CarName(carName),
                null
        );

        assertThat(car.toDto().getName())
                .isEqualTo(carName);
    }

    @ValueSource(ints = {
            1, 10, 100
    })
    @DisplayName("Car Dto 생성 테스트")
    @ParameterizedTest
    void toDtoLocationTest(int moveLocation) {
        Car car = new Car(
                new CarName("테스트카"),
                new AlwaysMoveStrategy(moveLocation)
        );
        car.move();

        assertThat(car.toDto().getLocation())
                .isEqualTo(moveLocation);
    }
}