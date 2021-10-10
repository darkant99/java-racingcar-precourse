package racinggame.domain;

import racinggame.dto.CarDto;
import racinggame.exception.InvalidCarNameException;
import racinggame.movestrategy.MoveStrategy;

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
        List<Car> winners = new ArrayList<>();

        Car oneOfWinners = Collections.max(cars, this::compareCarLocation);
        for (Car iCar : cars) {
            carLocationFilter(winners, oneOfWinners, iCar);
        }
        return new Cars(winners);
    }

    private int compareCarLocation(Car thisCar, Car thatCar) {
        return thisCar.compareLocation(thatCar);
    }

    private void carLocationFilter(List<Car> winners, Car oneOfWinners, Car currentCar) {
        // 현재 우승 자동차와 비교해 우승자와 위치값이 같다면 winners 리스트에 포함한다.
        if (oneOfWinners.compareLocation(currentCar) == 0) {
            winners.add(currentCar);
        }
    }

    public int size() {
        return cars.size();
    }

    public List<CarDto> toDtos() {
        List<CarDto> dtos = new ArrayList<>();
        for (Car iCar : cars) {
            dtos.add(iCar.toDto());
        }
        return dtos;
    }

    @Override
    public Iterator<Car> iterator() {
        return cars.iterator();
    }
}
