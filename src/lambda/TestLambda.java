package lambda;

import java.util.Timer;

/**
 * @author ZHOU
 * @date 2021/8/8 15:12
 */
public class TestLambda  extends Timer {

    public TimerTask schedule(final Runnable r, long delay) {
        final TimerTask task = new TimerTask() { public void run() { r.run(); }};
        this.schedule(task, delay);
        return task;
    }

    private void schedule(TimerTask task, long delay) {

    }
}
