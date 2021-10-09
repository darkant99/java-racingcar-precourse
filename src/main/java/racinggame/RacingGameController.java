package racinggame;

import racinggame.domain.*;
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
        RacingGame game = new RacingGame(inputCars());
        game.registerObserver(this);

        game.start(inputRoundSize());
    }

    private RoundSize inputRoundSize() {
        return new RoundSize(
                inputView.inputRoundSize()
        );
    }

    private Cars inputCars() {
        return Cars.of(
                inputView.inputCarNames(), MOVE_ROLE
        );
    }

    @Override
    public void updateRound(final Cars cars) {
        System.out.println("라운드 완료");
    }
}
