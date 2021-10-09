package racinggame.domain;

import racinggame.annotation.ThreadNotSafety;
import racinggame.exception.InvalidRoundSizeException;

import java.util.Objects;

import static racinggame.exception.ErrorMessage.ROUND_SIZE_NEGATIVE;

@ThreadNotSafety
public class RoundSize {
    private static final int END_SIZE = 0;

    private int size;

    public RoundSize(final int size) {
        validateNegativeSize(size);

        this.size = size;
    }

    private void validateNegativeSize(int size) {
        if (size <= END_SIZE) {
            throw new InvalidRoundSizeException(ROUND_SIZE_NEGATIVE);
        }
    }

    public boolean isCompleted() {
        return size <= END_SIZE;
    }

    public void doneRound() {
        --size;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final RoundSize roundSize = (RoundSize) o;
        return size == roundSize.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }
}
