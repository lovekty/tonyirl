package me.tonyirl.passport.test;

import me.tonyirl.api.core.RpcServiceDemo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by tony on 16/2/23.
 */
public class MainTest extends BaseTest {
    @Autowired
    private RpcServiceDemo rpcServiceDemo;

    @Test
    public void test(){
        String hello = rpcServiceDemo.sayHello();
        System.out.println(hello);
    }
}
