package nio.c2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * @author ZHOU
 * @date 2021/8/1 23:07
 */
public class TestServer {
    public static void main(String[] args) throws IOException {
        //单线程
        ByteBuffer buffer = ByteBuffer.allocate(16);
        //创建服务器
        ServerSocketChannel ssc= ServerSocketChannel.open();

        //绑定端口
        ssc.bind(new InetSocketAddress(8080));

        //创建连接集合
        ArrayList<SocketChannel> channels = new ArrayList<>();
        while (true){
            //accep与客户端连接，SocketChannel用来客户端通信
            System.out.println("connection...");
            SocketChannel accept = ssc.accept();
            System.out.println("connected....");
            channels.add(accept);
            channels.forEach(channel ->{
                try {
                    System.out.println("before read...");
                    //会阻塞线程
                    System.out.println(channel.read(buffer));
                    buffer.flip();
                    buffer.clear();
                    System.out.println("after read...");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                }
            });
        }

    }
}
