package me.tonyirl.core.dubbotest;

import me.tonyirl.api.core.RpcServiceDemo;

/**
 * Created by tony on 16/2/23.
 */
public class RpcServiceDemoImpl implements RpcServiceDemo {
    public String sayHello() {
        return "hello";
    }
}
