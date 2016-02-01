package me.tonyirl.common.orm.mybatis.annotation;

import java.lang.annotation.*;

/**
 * Created by tony on 16/2/1.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyBatisColumn {

    public String columnName();

    public String jdbcType() default "varchar";

    public boolean notNull() default true;

    public int length() default 64;

    public boolean uniq() default false;


}
