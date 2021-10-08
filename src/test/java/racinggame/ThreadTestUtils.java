package racinggame;

import racinggame.domain.RoundSize;

import java.util.concurrent.CountDownLatch;

public class ThreadTestUtils {
    private ThreadTestUtils() {
    }

    public static void run(Runnable runnable, int testSize) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(testSize);

        for (int i = 0; i < testSize; i++) {
            new Thread(
                    newRunnable(runnable, latch)
            ).start();
        }
        latch.await();
    }

    private static Runnable newRunnable(Runnable runnable, CountDownLatch latch) {
        return () -> {
            latch.countDown();
            runnable.run();
        };
    }
}
