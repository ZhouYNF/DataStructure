package netty.c2;

import java.lang.reflect.Executable;
import java.util.concurrent.*;

/**
 * @author ZHOU
 * @date 2021/8/8 0:20
 */
public class TestJdkFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<Integer> future = executorService.submit(() -> {
            Thread.sleep(1000);
            return 50;

        });
        System.out.println("等待结果");
        System.out.println("等待返回结果" + future.get());

        //future.cancel(true);

    }
}
