package racinggame.exception;

public class InvalidCarLocationException extends IllegalArgumentException {
    public InvalidCarLocationException(ErrorMessage errorMessage) {
        super(errorMessage.message());
    }
}
