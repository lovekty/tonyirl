package me.tonyirl.core.dubbotest;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import me.tonyirl.api.core.DemoFilter;

/**
 * Created by tony on 16/2/23.
 */
@Activate
public class MyDubboFilter implements Filter {

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Class<?> serviceClass = invoker.getInterface();
        URL url = invoker.getUrl();
        String value = invocation.getAttachment(DemoFilter.DEMO_PARAM_KEY);
        System.out.println("getInterface():" + serviceClass.getName());
        System.out.println("value:" + value);
        return invoker.invoke(invocation);
    }
}
