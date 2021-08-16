package nio.c4;

import sort.SelectSort;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * @author ZHOU
 * @date 2021/8/1 23:07
 */
public class TestServer {
    public static void main(String[] args) throws IOException {
        //1.创建selector,管理channel
        Selector open = Selector.open();

        //单线程
        //创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);//非阻塞模式


        //建立selector 与channl的联系（注册）,关注的事件，挂载
        ssc.register(open, SelectionKey.OP_ACCEPT, null);
        //绑定端口
        ssc.bind(new InetSocketAddress(8080));
        while (true) {
            //线程阻塞
            open.select();

            //包含了所有发生的事件
            Iterator<SelectionKey> iterator = open.selectedKeys().iterator();
            while (iterator.hasNext()) {
                System.out.println("key");
                SelectionKey next = iterator.next();
                //需要手动删除key，不然会报错
                iterator.remove();
                //区分事件类型
                if (next.isAcceptable()) {
                    System.out.println(next);
                    ServerSocketChannel channel = (ServerSocketChannel) next.channel();
                    SocketChannel accept = channel.accept();
                    accept.configureBlocking(false);
                    ByteBuffer allocate = ByteBuffer.allocate(16);
                    SelectionKey selectionKey = accept.register(open, SelectionKey.OP_READ, allocate);
                    //事件取消
                    //next.cancel();
                } else if (next.isReadable()) {
                    try {
                        SocketChannel channel = (SocketChannel) next.channel();

                        ByteBuffer attach = (ByteBuffer)next.attachment();
                        int read = channel.read(attach);//正常断开read的返回是-1
                        if (read == -1) {
                            next.cancel();
                        }else {
                            split(attach);
                            if (attach.position() == attach.limit()){
                                ByteBuffer allocate = ByteBuffer.allocate(attach.capacity() * 2);
                                attach.flip();
                                allocate.put(attach);
                                next.attach(allocate);
                            }
                            //attach.flip();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                        //因为客户端断开，需要将next删除
                        next.cancel();
                    } finally {
                    }

                }



            }

        }

    }

    private static void split(ByteBuffer source) {
        //切换读模式
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            int length = i + 1 - source.position();
            if (source.get(i) == '\n') {
                ByteBuffer target = ByteBuffer.allocate(length);
                for (int j = 0; j < length; j++) {
                    target.put(source.get());

                }
                for (int k = 0; k < target.limit(); k++) {
                    System.out.print((char) target.get(k));
                }
            }
        }
        //下次重上次没读完的地方开始读
        source.compact();
    }
}
