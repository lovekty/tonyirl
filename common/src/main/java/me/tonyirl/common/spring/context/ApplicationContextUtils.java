package me.tonyirl.common.spring.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by tony on 16-4-17.
 */
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext CONTEXT = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (null != CONTEXT) {
            throw new IllegalStateException("context has already been set!");
        }
        CONTEXT = applicationContext;
    }
}
