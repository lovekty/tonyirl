package me.tonyirl.api.core;

import com.alibaba.dubbo.common.extension.Adaptive;

/**
 * Created by tony on 16/2/23.
 */
@Adaptive
public class MyParamImpl implements MyParam {
    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    @Adaptive
    public String getValue() {
        return value;
    }
}
