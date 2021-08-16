package thread.concurrent;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author ZHOU
 * @date 2021/5/20 11:13`
 */
public class CopySet {
}

class Handler {
    void handle() {

    }
}

class X {
    private final CopyOnWriteArraySet<Handler> handlers = new CopyOnWriteArraySet<Handler>();

    public void addHandler(Handler h) {
        handlers.add(h);
    }

    private long internalState;

    private synchronized void changeState() {
        internalState = Long.parseLong(null);
    }

    public void update() {
        changeState();
        for (Handler handler : handlers) handler.handle();
    }
}