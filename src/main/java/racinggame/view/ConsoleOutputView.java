package racinggame.view;

public class ConsoleOutputView implements OutputView {
    @Override
    public void printException(final Exception exception) {
        System.out.println(
                Format.ERROR_MESSAGE.format(
                        exception.getMessage()
                )
        );
    }

    private enum Format {
        ERROR_MESSAGE("[ERROR] %s");

        private final String text;

        Format(final String text) {
            this.text = text;
        }

        public String format(Object... objs) {
            return String.format(text, objs);
        }
    }
}
