package nio.c3;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @author ZHOU
 * @date 2021/8/1 23:56
 */
public class TestClient {
    public static void main(String[] args) throws Exception {
        SocketChannel sc = SocketChannel.open();

        sc.connect(new InetSocketAddress("localhost",8080));
        System.out.println("waiting...");
    }
}
