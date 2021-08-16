package nio.c4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author ZHOU
 * @date 2021/8/5 23:48
 */
public class WriteServer {
    public static void main(String[] args) {
        try (ServerSocketChannel ssc = ServerSocketChannel.open()) {
            ssc.configureBlocking(false);

            Selector selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT, null);

            ssc.bind(new InetSocketAddress(8080));

            while (true) {
                selector.select();

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();

                    if (key.isAcceptable()) {
                        SocketChannel sc = ssc.accept();
                        sc.configureBlocking(false);
                        SelectionKey sckey = sc.register(selector, 0, null);

                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < 30000000; i++) {
                            stringBuilder.append("a");
                        }
                        ByteBuffer encode = Charset.defaultCharset().encode(stringBuilder.toString());

                        int write = sc.write(encode);
                        System.out.println(write);


                        if (encode.hasRemaining()) {
                            sckey.interestOps(sckey.interestOps() +  SelectionKey.OP_WRITE);

                            sckey.attach(encode);

                        }
                    } else if (key.isWritable()) {
                        
                    }
                }
            }

        } catch (IOException e) {
        }
    }
}
