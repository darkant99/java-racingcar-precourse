package racinggame.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CarLocation {
    private static final Map<Integer, CarLocation> CACHED = new HashMap<>();

    private final int location;

    private CarLocation(final int location) {
        this.location = location;
    }

    public static CarLocation of(final int location) {
        return cachedInstance(location);
    }

    public static CarLocation zero() {
        return of(0);
    }

    private static CarLocation cachedInstance(int location) {
        if (!CACHED.containsKey(location)) {
            registerCache(location);
        }
        return CACHED.get(location);
    }

    private static synchronized void registerCache(int location) {
        if (!CACHED.containsKey(location)) {
            CACHED.put(location, new CarLocation(location));
        }
    }

    public CarLocation move(int location) {
        return of(this.location + location);
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
