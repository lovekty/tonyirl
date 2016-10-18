package me.tonyirl.core.dubbotest;

import me.tonyirl.api.core.RpcServiceDemo;
import me.tonyirl.dto.core.Account;

/**
 * Created by tony on 16/2/23.
 */
public class RpcServiceDemoImpl implements RpcServiceDemo {
    public String sayHello() {
        return "hello";
    }

    @Override
    public Account mockAccount() {
        Account acc = new Account();
        acc.setUserId(1L);
        acc.setUsername("God");
        return acc;
    }
}
