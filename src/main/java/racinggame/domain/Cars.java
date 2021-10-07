package racinggame.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Cars {
    private static final String NAME_DELIMITER = ",";

    private final List<Car> cars;

    private Cars(final List<Car> cars) {
        this.cars = cars;
    }

    public static Cars of(final String names, final MoveStrategy moveStrategy) {
        // 쉼표(,)로 구분된 문자열을 Cars 객체로 변환한다.
        StringTokenizer tokenizer = new StringTokenizer(names, NAME_DELIMITER);

        List<Car> cars = new ArrayList<>();
        while(tokenizer.hasMoreTokens()) {
            Car newCar = new Car(
                    new CarName(tokenizer.nextToken()),
                    moveStrategy
            );
            cars.add(newCar);
        }
        return new Cars(cars);
    }
}
