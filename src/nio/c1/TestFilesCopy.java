package nio.c1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author ZHOU
 * @date 2021/8/1 22:39
 */
public class TestFilesCopy {
    public static void main(String[] args) throws IOException {
        String source = "F:\\tomcat\\新建文件夹\\apache-tomcat-6.0.18";
        String target = "F:\\tomcat\\新建文件夹\\apache-tomcat-6.0.18aaaa";

        Files.walk(Paths.get(source)).forEach(path -> {
            try {
                String targetName = path.toString().replace(source, target);
                //判断是目录就创建
                if (Files.isDirectory(path)) {
                    Files.createDirectory(Paths.get(targetName));
                    //文件就拷贝
                } else if (Files.isRegularFile(path)) {
                    Files.copy(path, Paths.get(targetName));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }


        });
    }
}
