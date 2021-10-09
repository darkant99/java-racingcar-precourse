package racinggame.domain;

import racinggame.exception.InvalidCarLocationException;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static racinggame.exception.ErrorMessage.CAR_LOCATION_NEGATIVE;

@ThreadSafety
public class CarLocation {
    private static final int MIN_LOCATION = 0;

    private static final Map<Integer, CarLocation> CACHED = new ConcurrentHashMap<>();

    private final int location;

    private CarLocation(final int location) {
        validateNegativeSize(location);

        this.location = location;
    }

    private void validateNegativeSize(int size) {
        if (size < MIN_LOCATION) {
            throw new InvalidCarLocationException(CAR_LOCATION_NEGATIVE);
        }
    }

    public static CarLocation of(final int location) {
        if (!CACHED.containsKey(location)) {
            CACHED.put(location, new CarLocation(location));
        }
        return CACHED.get(location);
    }

    public static CarLocation zero() {
        return of(0);
    }

    public CarLocation move(int location) {
        return of(this.location + location);
    }

    public int value() {
        return location;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CarLocation that = (CarLocation) o;
        return location == that.location;
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }
}
