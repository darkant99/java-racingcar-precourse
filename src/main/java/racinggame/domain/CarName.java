package racinggame.domain;

import racinggame.exception.InvalidCarNameException;

import static racinggame.exception.ErrorMessage.CAR_NAME_EMPTY;
import static racinggame.exception.ErrorMessage.CAR_NAME_IS_MAX_LENGTH_OVER;

public class CarName {
    private final String name;

    public CarName(final String name) {
        validateNameLength(name);

        this.name = name;
    }

    private void validateNameLength(final String name) {
        String nameForValidate = name.toUpperCase().trim();
        if (nameForValidate.isEmpty()) {
            throw new InvalidCarNameException(CAR_NAME_EMPTY);
        }
        if (nameForValidate.length() > 5) {
            throw new InvalidCarNameException(CAR_NAME_IS_MAX_LENGTH_OVER);
        }
    }
}
