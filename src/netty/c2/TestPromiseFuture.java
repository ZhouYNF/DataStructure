package netty.c2;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;

import java.util.concurrent.ExecutionException;

/**
 * @author ZHOU
 * @date 2021/8/8 0:38
 */
public class TestPromiseFuture {
    public static void main(String[] args) {
        try {
            EventLoop next = new NioEventLoopGroup().next();


            DefaultPromise<Integer> promise = new DefaultPromise<Integer>(next);

            new Thread(() -> {
                try {
                    int i = 1/0;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    promise.setFailure(e);
                }

                promise.setSuccess(80);
            }).start();


            System.out.println(promise.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
