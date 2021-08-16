package thread;

import com.sun.org.glassfish.gmbal.Description;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ZHOU
 * @date 2021/5/18 14:49
 */
public class Mythread2 {
    public static void main(String[] args) {


        List<User> list = new ArrayList<>();
        list.add(new User("1", 111));
        list.add(new User("1", 222));

        //循环遍历
        /*list.forEach((User user) -> {
            System.out.println(user.age);
        });*/

        //转成流的形式
        Stream<User> stream = list.stream();
        /*long count = stream.filter(p -> p.getAge() > 112).map((User user) -> {
            return 50;
        }).count();*/


      /*  List<User> collect = stream.filter(p -> p.getAge() > 110)
                .distinct()
                .collect(Collectors.toList());*/

        stream.filter(p -> p.getAge() > 110).forEach(System.out::println);
        //System.out.println(collect.toString());
    }
}
