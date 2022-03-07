package com.github.jiangxch.mypring.util;


/**
 * @author: jiangxch
 * @date: 2022/3/6 12:44 AM
 */
public class AssertUtil {
    public static void notNull(Object annotationType, String exceptionMsg) {
        if (annotationType == null) {
            throw new IllegalArgumentException(exceptionMsg);
        }
    }
}
