package nio.c5;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ZHOU
 * @date 2021/8/6 22:41
 */
public class MultiThreadServer {
    public static void main(String[] args) {
        Thread.currentThread().setName("boss");

        //创建服务器
        try (ServerSocketChannel ssc = ServerSocketChannel.open()) {
            //设置非阻塞模式
            ssc.configureBlocking(false);
            //创建选择器
            Selector boss = Selector.open();

            //将选择器绑定到服务其上，监听连接
            ssc.register(boss, SelectionKey.OP_ACCEPT, null);

            //设置端口
            ssc.bind(new InetSocketAddress(8080));

            //Runtime.getRuntime().availableProcessors()获取CPU的核心数
            //设置多个工作线程
            Worker[] workers = new Worker[Runtime.getRuntime().availableProcessors()];
            for (int i = 0; i < workers.length; i++) {
                workers[i] = new Worker("woker-" + i);
            }

            //自增，轮询
            AtomicInteger index = new AtomicInteger();
            //Worker worker = new Worker("woker-0");

            while (true) {
                //阻塞，等待连接
                boss.select();
                System.out.println("===================>boss执行");
                //多连接，设置迭代模式
                Iterator<SelectionKey> iter = boss.selectedKeys().iterator();

                while (iter.hasNext()) {
                    //获取到服务器
                    SelectionKey key = iter.next();

                    //删除掉迭代器中的服务器，防止空转
                    iter.remove();

                    //判断监听的是否是连接模式
                    if (key.isAcceptable()) {
                        /*ServerSocketChannel channel = (ServerSocketChannel)key.channel();
                        channel.accept()*/
                        //通过服务器，也可以通过key来拿，获取到一个连接通道
                        SocketChannel sc = ssc.accept();
                        System.out.println("===================>获取连接通道");
                        //通道设置非阻塞模式
                        sc.configureBlocking(false);

                        //将通道交给工作线程处理，达到多线程处理数据的效果，实现多路复用
                        workers[index.getAndIncrement() % workers.length].register(sc);
                        //worker.register(sc);

                    }
                }
            }


        } catch (IOException e) {
        }
    }


    static class Worker implements Runnable {
        private Selector selector;
        private String name;
        private Thread thread;
        //向队列中添加任务，但这个任务斌没有被执行
        private ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue<>();

        private volatile boolean start = false;

        public Worker(String name) {
            this.name = name;
        }

        public void register(SocketChannel sc) throws IOException {

            if (!start) {
                System.out.println("==============》首次加载");
                //首次加载创建选择器
                selector = Selector.open();
                //获取线程
                thread = new Thread(this, name);
                //启动线程
                thread.start();

                start = true;

            }
            //通过队列的将SocketChannel从boss线程，交给新创建的工作线程
            queue.add(() -> {
                try {
                    System.out.println("=======================>加入队列");
                    //将通道存放至队列中
                    sc.register(selector, SelectionKey.OP_READ, null);
                } catch (ClosedChannelException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("==============》主动唤醒");
            //首次主动唤醒线程，加载监听读事件
            selector.wakeup();

        }

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("==============》1111");
                    //选择器阻塞监听，首次会被wakeup主动唤醒
                    selector.select();
                    //从队列中拿出SocketChannel通道
                    Runnable task = queue.poll();

                    if (task != null) {
                        task.run();
                    }
                    System.out.println(selector);
                    System.out.println("============selector=================");

                    Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        iter.remove();

                        if (key.isReadable()) {
                            ByteBuffer buffer = ByteBuffer.allocate(16);
                            SocketChannel channel = (SocketChannel) key.channel();

                            channel.read(buffer);

                            buffer.flip();
                            for (int i = 0; i < buffer.capacity(); i++) {
                                System.out.print((char) buffer.get(i));
                                buffer.clear();
                            }

                            System.out.println(channel);
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }


    }
}
