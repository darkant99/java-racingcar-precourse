package racinggame.domain;

import java.util.Objects;

public class CarLocation {
    private final int location;

    public CarLocation(final int location) {
        this.location = location;
    }

    public static CarLocation zero() {
        return InnerLazyClass.ZERO;
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

    private static class InnerLazyClass {
        public static CarLocation ZERO = new CarLocation(0);
    }
}
