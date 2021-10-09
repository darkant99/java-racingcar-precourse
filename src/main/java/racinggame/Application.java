package racinggame;

import racinggame.view.ConsoleInputView;
import racinggame.view.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        new RacingGameController(
                new ConsoleInputView(),
                new ConsoleOutputView()
        ).run();
    }
}
