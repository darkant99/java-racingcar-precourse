package racinggame.view;

import racinggame.dto.CarDto;
import racinggame.util.RepeatString;

import java.util.ArrayList;
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
    public void printCarLocation(final CarDto car) {
        System.out.println(
                Format.CAR_LOCATION.format(
                        car.getName(),
                        CAR_LOCATION_REPEAT_STRING.repeat(car.getLocation())
                )
        );
    }

    @Override
    public void printWinners(final List<CarDto> winners) {
        String winnerNamesText = makeJoinedText(
                CAR_NAMES_DELIMITER,
                winners,
                CarDto::getName
        );
        System.out.println(
                Format.WINNERS.format(winnerNamesText)
        );
    }

    private <T> String makeJoinedText(String delimiter, List<T> objs, Function<T, String> getTextFunc) {
        List<String> texts = new ArrayList<>();
        objs.forEach(
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
