package racinggame.exception;

public enum ErrorMessage {
    ROUND_SIZE_NEGATIVE("진행할 라운드는 음수일 수 없습니다."),
    CAR_NAME_EMPTY("자동차 이름은 공백일 수 없습니다."),
    CAR_NAME_DUPLICATE("자동차 이름은 중복될 수 없습니다."),
    CAR_NAME_IS_MAX_LENGTH_OVER("유효하지 않은 자동차 이름 입니다.");

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
