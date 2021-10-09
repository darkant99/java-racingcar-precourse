package racinggame.view;

import racinggame.util.RepeatString;
import racinggame.domain.CarLocation;
import racinggame.domain.CarName;
import racinggame.domain.Cars;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class ConsoleOutputView implements OutputView {
    private final RepeatString CAR_LOCATION_REPEAT_STRING = new RepeatString("-");
    private final String CAR_NAMES_DELIMITER = ",";

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

    @Override
    public void printWinners(final Cars winners) {
        String winnerNamesText = makeJoinedText(
                CAR_NAMES_DELIMITER,
                winners.iterator(),
                iCar -> iCar.name().value()
        );
        System.out.println(
                Format.WINNERS.format(winnerNamesText)
        );
    }

    private <T> String makeJoinedText(String delimiter, Iterator<T> objs, Function<T, String> getTextFunc) {
        List<String> texts = new ArrayList<>();
        objs.forEachRemaining(
                iObject -> texts.add(getTextFunc.apply(iObject))
        );
        return String.join(delimiter, texts);
    }

    private enum Format {
        ERROR_MESSAGE("[ERROR] %s"),
        RESULT_TITLE("실행 결과"),
        CAR_LOCATION("%s : %s"),
        WINNERS("최종 우승자는 %s 입니다.");

        private final String text;

        Format(final String text) {
            this.text = text;
        }

        public String format(Object... objs) {
            return String.format(text, objs);
        }
    }
}
