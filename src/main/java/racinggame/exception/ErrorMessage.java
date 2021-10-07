package racinggame.exception;

public enum ErrorMessage {
    INVALID_CAR_NAME("유효하지 않은 자동차 이름 입니다.");

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
