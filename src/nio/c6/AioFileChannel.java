package nio.c6;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author ZHOU
 * @date 2021/8/7 1:12
 */
public class AioFileChannel {
    public static void main(String[] args) throws IOException {
        try (AsynchronousFileChannel channel = AsynchronousFileChannel.open(Paths.get("data01.txt"), StandardOpenOption.READ)) {

            ByteBuffer buffer = ByteBuffer.allocate(16);
            System.out.println("===================>read begin");
            //1.参数1，ByteBuffer
            //2.读取的起始位置
            //3.附件
            //4.回调对象CompletionHandler
            channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override  //成功
                public void completed(Integer result, ByteBuffer attachment) {
                    System.out.println(attachment.flip());

                }

                @Override //失败
                public void failed(Throwable exc, ByteBuffer attachment) {

                }
            });
            System.out.println("=====================>结束");
        } catch (IOException e) {

        }

        System.in.read();
    }
}
