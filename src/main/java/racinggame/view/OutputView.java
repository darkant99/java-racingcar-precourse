package racinggame.view;

import racinggame.dto.CarDto;

import java.util.List;

public interface OutputView {
    void printDividing();

    void printException(Exception exception);

    void printGameResultTitle();

    void printCarLocation(CarDto car);

    void printWinners(List<CarDto> winners);
}
