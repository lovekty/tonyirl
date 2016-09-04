package me.tonyirl.common.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tony on 2016/9/4.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
public class Java8Test {

    @Test
    public void testLambda() {
        Thread t = new Thread(() -> System.out.println("hello world"));
        t.start();
    }

    @Test
    public void testStream() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<?> result = list.stream().filter(integer -> integer % 2 == 0).map(integer -> {
            integer /= 2;
            return String.valueOf(integer);
        }).collect(Collectors.toList());
        result.forEach(System.out::println);
    }
}
