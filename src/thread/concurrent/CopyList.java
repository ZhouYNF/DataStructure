package thread.concurrent;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author ZHOU
 * @date 2021/5/30 16:05
 */
public class CopyList {
    public static void main(String[] args) {
        Vector<Object> vector = new Vector<>();
        List<Object> objects = Collections.synchronizedList(new ArrayList<>());
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
        

    }

}
