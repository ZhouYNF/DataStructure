package CASDemo;

import sun.misc.Unsafe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ZHOU
 * @date 2021/7/28 17:49
 */
public class CASDemo {
    public static void main(String[] args) {
//        Unsafe

        Lock lock = new ReentrantLock();

        lock.lock();


    }
}
