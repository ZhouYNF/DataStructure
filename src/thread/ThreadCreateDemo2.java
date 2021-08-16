package thread;

import jdk.nashorn.internal.ir.CallNode;

import java.util.ArrayList;

/**
 * @author ZHOU
 * @date 2021/3/25 19:50
 */
public class ThreadCreateDemo2 {
    public static void main(String[] args) {
        Runnable runnable = new MyRunnable()::run;
        Thread thread = new Thread(runnable);
        thread.start();
    }

     /*byte[] array =  new byte[1*1024*1024];

    public static void main(String[] args) {
        ArrayList<ThreadCreateDemo2> demo2 = new ArrayList<>();
        int count =0;


            try {
                while (true){
                    count ++;

                    demo2.add(new ThreadCreateDemo2());

                }
            }catch (Error error){
                System.out.println("count ==================》"+count);
                error.printStackTrace();
            }

    }*/
}
