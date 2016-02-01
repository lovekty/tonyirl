package me.tonyirl.common.orm.mybatis.annotation;

import java.lang.annotation.*;

/**
 * Created by tony on 16/2/1.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyBatisEntity {

    String tableName();

    String[] primaryKey() default {"id"};
}
