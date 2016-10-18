package me.tonyirl.passport.test;

import me.tonyirl.api.core.RpcServiceDemo;
import me.tonyirl.dto.core.Account;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by tony on 16/2/23.
 */
public class MainTest extends BaseTest {
    @Autowired
    private RpcServiceDemo rpcServiceDemo;

    @Test
    public void test() {
        String hello = rpcServiceDemo.sayHello();
        System.out.println(hello);
    }

    @Test
    public void testJava8toJava5() {
        Account acc = rpcServiceDemo.mockAccount();
        System.out.println("Account userId:" + acc.getUserId() + " username:" + acc.getUsername());
    }
}
