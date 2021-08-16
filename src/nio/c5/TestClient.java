package nio.c5;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author ZHOU
 * @date 2021/8/6 23:32
 */
public class TestClient {
    public static void main(String[] args) {
        try (SocketChannel open = SocketChannel.open()) {
            open.connect(new InetSocketAddress("localhost",8080));
            open.write(Charset.defaultCharset().encode("111111111111111111111111111"));
            System.in.read();
        } catch (IOException e) {
        }
    }
}
