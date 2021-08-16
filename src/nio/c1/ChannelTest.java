package nio.c1;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author ZHOU
 * @date 2021/7/26 15:35
 */
public class ChannelTest {

    @Test
    public void write() {
        try (FileOutputStream fos = new FileOutputStream("data01.txt")) {
            FileChannel channel = fos.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("Hello,world".getBytes());
            buffer.flip();
            channel.write(buffer);
            channel.close();
            ;
            System.out.println("数据读写完成！！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
