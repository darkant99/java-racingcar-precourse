package racinggame.domain;

import racinggame.exception.InvalidCarNameException;

import java.util.*;

import static racinggame.exception.ErrorMessage.CAR_NAME_DUPLICATE;

public class Cars implements Iterable<Car> {
    private static final String NAME_DELIMITER = ",";

    private final List<Car> cars;

    private Cars(final List<Car> cars) {
        validateDuplicate(cars);

        this.cars = cars;
    }

    private void validateDuplicate(List<Car> cars) {
        if (cars.size() != new HashSet<>(cars).size()) {
            throw new InvalidCarNameException(CAR_NAME_DUPLICATE);
        }
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

    public void move() {
        cars.forEach(Car::move);
    }

    public Cars winners() {
        return null;
    }

    public int size() {
        return cars.size();
    }

    @Override
    public Iterator<Car> iterator() {
        return cars.iterator();
    }

}
