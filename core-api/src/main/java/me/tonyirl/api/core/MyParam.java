package me.tonyirl.api.core;

import com.alibaba.dubbo.common.extension.SPI;

/**
 * Created by tony on 16/2/23.
 */
@SPI
public interface MyParam {
    String getValue();
}
