package racinggame.util;

public class RepeatString {
    private final String unit;

    public RepeatString(final String unit) {
        this.unit = unit;
    }

    public String repeat(int size) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append(unit);
        }
        return builder.toString();
    }
}
