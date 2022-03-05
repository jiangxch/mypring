package com.github.jiangxch.mypring.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author: jiangxch
 * @date: 2022/3/3 12:03 AM
 */
@Target(ElementType.TYPE)
public @interface ComponentScan {
    String value();
}
