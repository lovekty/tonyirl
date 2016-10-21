package me.tonyirl.common.test;

import me.tonyirl.common.utils.CglibBeanCopierUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
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

    @Test
    public void testBeanCopier() throws ExecutionException {
        User u = new User();
        u.setId(1L);
        u.setName("tony");
        u.setGender(1);

        User u2 = new User();
        User u3 = new User();
        User u4 = new User();
        User2 u5 = new User2();
        CglibBeanCopierUtils.copy(u, u2, false);
        CglibBeanCopierUtils.copy(u, u3, false);
        CglibBeanCopierUtils.copy(u, u4, true);
        CglibBeanCopierUtils.copy(u, u5, true);
        System.out.println(u2.getId());
        System.out.println(u2.getName());
        System.out.println(u2.getGender());
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

    public static class User2 {
        private int id;
        private String name;
        private String gender;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }
}

