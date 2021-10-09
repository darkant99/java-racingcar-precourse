package racinggame;

import racinggame.domain.*;
import racinggame.movestrategy.MoveStrategy;
import racinggame.movestrategy.RandomMoveStrategy;
import racinggame.observer.RacingGameObserver;
import racinggame.view.InputView;
import racinggame.view.OutputView;

public class RacingGameController implements RacingGameObserver {
    private static final MoveStrategy MOVE_ROLE = new RandomMoveStrategy();

    private final InputView inputView;
    private final OutputView outputView;

    public RacingGameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        RacingGame game = new RacingGame(newCars());
        game.registerObserver(this);

        game.start(newRoundSize());
    }

    private RoundSize newRoundSize() {
        try {
            return new RoundSize(inputView.inputRoundSize());
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return newRoundSize();
        }
    }

    private Cars newCars() {
        try {
            return Cars.of(inputView.inputCarNames(), MOVE_ROLE);
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return newCars();
        }
    }

    @Override
    public void gameStart() {
        outputView.printDividing();

        outputView.printGameResultTitle();
    }

    @Override
    public void gameEnd(final Cars cars) {
        outputView.printWinners(cars.winners());
    }

    @Override
    public void updateRound(final Cars cars) {
        for(Car iCar : cars) {
            outputView.printCarLocation(iCar.name(), iCar.location());
        }
        outputView.printDividing();
    }
}
