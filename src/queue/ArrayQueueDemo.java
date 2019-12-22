package queue;

import java.util.Scanner;

/**
 * 单次使用的队列，不能重复使用
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            System.out.println("---------------------------");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': //取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~");


    }

    static class ArrayQueue {
        private int maxSize; //队列的最大位置
        private int front; //队列的当前消费位置
        private int rear; //队列的当前存储位置
        private int[] arr; //队列数组

        /**
         * 创建一个队列
         *
         * @param maxSize
         */
        public ArrayQueue(int maxSize) {
            arr = new int[maxSize];
            front = -1;
            rear = -1;
            this.maxSize = maxSize;
        }


        // 判断队列是否满
        public boolean isFull() {
            return rear == maxSize - 1;
        }

        // 判断队列是否为空
        public boolean isEmpty() {
            return front == rear;
        }

        // 添加数据到队列
        public void addQueue(int n) {
            if (isFull()) {
                System.out.println("数据已满，不可添加！！！");
                return;
            }
            rear++;
            arr[rear] = n;
        }

        // 获取队列的数据, 出队列
        public int getQueue() {
            if (isEmpty()) {
                throw new RuntimeException("没有数据不能消费！！！");
            }
            front++;
            return arr[front];
        }

        // 显示队列的所有数据
        public void showQueue() {
            if (isEmpty()) {
                System.out.println("数组没有数据！！");
                return;
            }
            for (int i = 0; i < arr.length; i++) {
                System.out.printf("arr[%s]=%s\n", i, arr[i]);
            }
        }

        // 显示队列的头数据， 注意不是取出数据
        public int headQueue() {
            if (isEmpty()) {
                throw new RuntimeException("没有数据！！");
            }
            return arr[front + 1];
        }
    }
}
