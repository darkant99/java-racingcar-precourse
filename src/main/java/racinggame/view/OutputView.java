package racinggame.view;

import racinggame.domain.CarLocation;
import racinggame.domain.CarName;

public interface OutputView {
    void printDividing();

    void printException(Exception exception);

    void printGameResultTitle();

    void printCarLocation(CarName carName, CarLocation location);
}
