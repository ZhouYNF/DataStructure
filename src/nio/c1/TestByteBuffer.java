package nio.c1;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author ZHOU
 * @date 2021/8/4 0:05
 */
public class TestByteBuffer {
    public static void main(String[] args) {
        m1();
    }
    private static void m3() {
        //1.字符串转byteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.put("Hello".getBytes());

        System.out.println(buffer);

        //2.Charset
        ByteBuffer buffer1 = StandardCharsets.UTF_8.encode("Hello");
        System.out.println(buffer1);
        System.out.println((char) buffer1.get());

        //3.wrap
        ByteBuffer buffer2 = ByteBuffer.wrap("Hello".getBytes());
        System.out.println(buffer2);
        //23自动切换读模式

        //byteBuffer转字符串，需要切换读模式
        String s = StandardCharsets.UTF_8.decode(buffer2).toString();
        System.out.println(s);
    }

    private static void m2() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a','b','c','d'});
        //读模式
        buffer.flip();

        //重头开始读
        System.out.println(buffer.get(new byte[4]));
        System.out.println(buffer.rewind());
        System.out.println((char) buffer.get()); //a

        System.out.println(buffer.rewind());

        //mark & reset
        //mark做标记，reset可以回到mark的标记位置
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        System.out.println(buffer.mark());//mark做标记
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        System.out.println(buffer.reset());//reset可以回到mark的标记位置
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());


        //potion 位置不会改变
        System.out.println((char) buffer.get(3));
    }

    private static void m1() {
        //读取文件
        try (FileChannel channel = new FileInputStream("data01.txt").getChannel()) {
            //获取字节缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(5);
            while (true) {
                //将读到的问题，写入字节缓冲区.返回读到的数量
                int read = channel.read(buffer);

                if (read == -1) {
                    break;
                }
                //切换读模式
                buffer.flip();

                while (buffer.hasRemaining()) {
                    System.out.println((char) buffer.get());
                }
                //切换写模式
                buffer.clear();
                /*或则
                buffer.compact();*/

            }

        } catch (IOException e) {
        }
    }
}
