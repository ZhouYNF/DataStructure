package nio.c4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author ZHOU
 * @date 2021/8/5 23:58
 */
public class WriteClient {
    public static void main(String[] args) {
        try (SocketChannel sc = SocketChannel.open()) {
            sc.connect(new InetSocketAddress(8080));

            int count=0;
            while (true) {
                ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);

                count +=sc.read(buffer);
                System.out.println(count);
                buffer.clear();
            }
        } catch (IOException e) {
        }
    }
}
