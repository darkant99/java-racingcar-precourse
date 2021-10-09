package racinggame.view;

import racinggame.RepeatString;
import racinggame.domain.CarLocation;
import racinggame.domain.CarName;

public class ConsoleOutputView implements OutputView {
    private final RepeatString CAR_LOCATION_REPEAT_STRING = new RepeatString("-");

    @Override
    public void printDividing() {
        System.out.println();
    }

    @Override
    public void printException(final Exception exception) {
        System.out.println(
                Format.ERROR_MESSAGE.format(
                        exception.getMessage()
                )
        );
    }

    @Override
    public void printGameResultTitle() {
        System.out.println(Format.RESULT_TITLE.text);
    }

    @Override
    public void printCarLocation(final CarName carName, final CarLocation location) {
        System.out.println(
                Format.CAR_LOCATION.format(
                        carName.value(),
                        CAR_LOCATION_REPEAT_STRING.repeat(location.value())
                )
        );
    }

    private enum Format {
        ERROR_MESSAGE("[ERROR] %s"),
        RESULT_TITLE("실행 결과"),
        CAR_LOCATION("%s : %s"),
        CAR_LOCATION_UNIT("-");

        private final String text;

        Format(final String text) {
            this.text = text;
        }

        public String format(Object... objs) {
            return String.format(text, objs);
        }
    }
}
