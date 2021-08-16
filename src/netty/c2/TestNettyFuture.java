package netty.c2;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.concurrent.ExecutionException;

/**
 * @author ZHOU
 * @date 2021/8/8 0:29
 */
public class TestNettyFuture {
    public static void main(String[] args) {
        try {
            NioEventLoopGroup group = new NioEventLoopGroup();

            EventLoop next = group.next();

            Future<Integer> submit = next.submit(() -> {
                Thread.sleep(1000);
                return 70;
            });

            //主线程获取结果
            System.out.println("等待返回结果" + submit.get());

            submit.addListener(future -> {
                //异步方式获取结果
                System.out.println("等待返回结果" + submit.get());
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
        }

    }
}
