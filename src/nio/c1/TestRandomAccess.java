package nio.c1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author ZHOU
 * @date 2021/8/3 23:53
 */
public class TestRandomAccess {
    public static void main(String[] args) {
        //m1();
        m2();

    }

    private static void m2() {
        //集中写入
        ByteBuffer b1 = StandardCharsets.UTF_8.encode("Hello");
        ByteBuffer b2 = StandardCharsets.UTF_8.encode("world");
        ByteBuffer b3 = StandardCharsets.UTF_8.encode("你好");

        try (FileChannel channel = new RandomAccessFile("data01.txt", "rw").getChannel()) {
            System.out.println(channel.write(new ByteBuffer[]{b1, b2, b3}));


        } catch (IOException e) {
        }
    }

    private static void m1() {
        //分散读取
        try (FileChannel channel = new RandomAccessFile("data01.txt", "r").getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(3);
            ByteBuffer buffer2 = ByteBuffer.allocate(3);
            ByteBuffer buffer3 = ByteBuffer.allocate(5);

            channel.read(new ByteBuffer[]{buffer, buffer2, buffer3});
            System.out.println(buffer.flip());
            System.out.println(buffer2.flip());
            System.out.println(buffer3.flip());

            System.out.println((char) buffer.get());
            System.out.println((char) buffer.get());
            System.out.println((char) buffer.get());
            System.out.println("=============>");
            System.out.println((char) buffer2.get());
            System.out.println((char) buffer2.get());
            System.out.println((char) buffer2.get());
            System.out.println("=============>");
            System.out.println((char) buffer3.get());
            System.out.println((char) buffer3.get());
            System.out.println((char) buffer3.get());

        } catch (IOException e) {
        }
    }
}
