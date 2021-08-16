package nio.c1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @author ZHOU
 * @date 2021/8/1 14:09
 */
public class TestFileChannelTrancsferTo {
    public static void main(String[] args) {
        try (
                FileChannel from = new FileInputStream("data.txt").getChannel();
                FileChannel to = new FileOutputStream("todo.txt").getChannel();
        ) {
            //全部数据
            long size = from.size();

            for (long left = size; left>0;) {
                System.out.println("position:"+(size - left)+"left:"+left);
                //剩余数据
               left -= from.transferTo((size - left), left, to);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
