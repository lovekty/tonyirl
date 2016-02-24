package me.tonyirl.api.core;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tony on 16/2/24.
 */
@Activate(group = Constants.CONSUMER)
public class DemoFilter implements Filter {

    public static final String DEMO_PARAM_KEY = "demo";

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        URL url = invoker.getUrl();
        if (url.hasParameter(DEMO_PARAM_KEY) && invocation instanceof RpcInvocation) {
            RpcInvocation inv = (RpcInvocation) invocation;
            String value = url.getParameter(DEMO_PARAM_KEY);
            Map<String, String> attachment = new HashMap<String, String>();
            attachment.put(DEMO_PARAM_KEY, value);
            inv.addAttachmentsIfAbsent(attachment);
        }
        return invoker.invoke(invocation);
    }
}
