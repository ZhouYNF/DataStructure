package nio.c1;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author ZHOU
 * @date 2021/8/1 13:37
 */
public class TestByteBufferExam {
    public static void main(String[] args) {
        ByteBuffer source = ByteBuffer.allocate(32);
        source.put("Hello,world\nI'm zhangsan \nHo".getBytes());
        split(source);
        source.put("w are you ?\n".getBytes());
        split(source);
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
