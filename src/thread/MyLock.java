package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ZHOU
 * @date 2021/5/18 22:58
 */
public class MyLock {
    public static void main(String[] args) {
        User user = new User("1",50);
        new Thread(() ->{ for (int i = 1; i <50 ; i++) user.sale(); },"A").start();

        new Thread(() ->{ for (int i = 1; i <50 ; i++) user.sale(); },"B").start();

        new Thread(() ->{ for (int i = 1; i <50 ; i++) user.sale(); },"C").start();
    }



}
class Ticket{
    private  int number = 30;

    Lock lock =  new ReentrantLock();

    public void sale(){
        lock.lock();

        try {

            if (number == 20){
                Thread.sleep(11000);
            }
            if (number >0){
                System.out.println(Thread.currentThread()+"============================="+(number--)+"======================"+number);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //;lock.unlock();
        }
    }

}