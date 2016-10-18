package me.tonyirl.common.test;

import org.junit.Test;

import java.util.ArrayList;
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

    @Test
    public void test() {
        List<User> users = new ArrayList<>();

        List<Long> ids = users.stream().map(User::getId).collect(Collectors.toList());

        List<Long> ids2 = new ArrayList<>(users.size());
        ids2.addAll(users.stream().map(User::getId).collect(Collectors.toList()));

    }

    @Test
    public void testLbdNAnm() {
        String abc = "123";
        new Thread(() -> System.out.println(abc));
//        new Thread(() -> abc = "abc");
    }

    @FunctionalInterface
    interface I {
        int dosth(String str);
    }

    public static class User {
        private long id;
        private String name;
        private int gender;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }
    }
}

