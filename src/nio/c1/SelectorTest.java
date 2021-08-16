package nio.c1;

import org.junit.Test;

import java.io.IOException;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

/**
 * @author ZHOU
 * @date 2021/7/26 22:16
 */
public class SelectorTest {
    public static void main(String[] args) throws IOException {

        try (AsynchronousFileChannel open = AsynchronousFileChannel.open(Paths.get(""), StandardOpenOption.READ)) {

        } catch (IOException e) {
        }
    }

}
