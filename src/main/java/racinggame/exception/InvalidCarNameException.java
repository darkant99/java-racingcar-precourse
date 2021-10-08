package racinggame.exception;

public class InvalidCarNameException extends IllegalArgumentException {
    public InvalidCarNameException(ErrorMessage errorMessage) {
        super(errorMessage.message());
    }
}
