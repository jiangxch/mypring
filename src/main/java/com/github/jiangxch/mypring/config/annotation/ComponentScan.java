package com.github.jiangxch.mypring.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: jiangxch
 * @date: 2022/3/3 12:03 AM
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ComponentScan {
    /**
     *
     * @return 逗号分割的包名
     */
    String value();
}
