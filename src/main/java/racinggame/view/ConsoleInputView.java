package racinggame.view;

import java.util.Scanner;

public class ConsoleInputView implements InputView {
    private final Scanner scanner;

    public ConsoleInputView() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String inputCarNames() {
        return nextLine(Text.INPUT_CAR_NAMES);
    }

    @Override
    public int inputRoundSize() {
        return nextLineAsInt(Text.INPUT_ROUND_SIZE);
    }

    private String nextLine(Text guideText) {
        System.out.println(guideText.text);

        return scanner.nextLine();
    }

    private int nextLineAsInt(Text guideText) {
        try {
            return Integer.parseInt(nextLine(guideText));
        } catch (NumberFormatException e) {
            System.out.println(Text.NUMBER_FORMAT_ERROR.text);
            return nextLineAsInt(guideText);
        }
    }

    private enum Text {
        NUMBER_FORMAT_ERROR("[ERROR] 숫자만 입력 해주세요."),
        INPUT_CAR_NAMES("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)"),
        INPUT_ROUND_SIZE("시도할 회수는 몇회인가요?");

        Text(final String text) {
            this.text = text;
        }

        private final String text;
    }
}
