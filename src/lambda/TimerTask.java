package lambda;


import java.util.Timer;

/**
 * @author ZHOU
 * @date 2021/8/8 15:12
 */
public class TimerTask {


    public static void main(String[] args) {
        TestLambda timer = new TestLambda();
        timer.schedule(()->{
//代码...
        }, 1000);
//或者
        //timer.schedule(this::run, 1000);
    }

}
