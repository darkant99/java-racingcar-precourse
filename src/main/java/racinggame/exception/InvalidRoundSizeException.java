package racinggame.exception;

public class InvalidRoundSizeException extends IllegalArgumentException {
    public InvalidRoundSizeException(ErrorMessage errorMessage) {
        super(errorMessage.message());
    }
}
