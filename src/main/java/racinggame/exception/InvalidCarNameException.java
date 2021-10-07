package racinggame.exception;

import static racinggame.exception.ErrorMessage.*;

public class InvalidCarNameException extends IllegalArgumentException {
    public InvalidCarNameException(ErrorMessage errorMessage) {
        super(errorMessage.message());
    }
}
