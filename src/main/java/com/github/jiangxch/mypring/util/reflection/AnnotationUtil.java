package com.github.jiangxch.mypring.util.reflection;

import com.github.jiangxch.mypring.util.AssertUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author: jiangxch
 * @date: 2022/3/6 12:35 AM
 */
public class AnnotationUtil {

    public static <A extends Annotation> A findAnnotation(Class<?> clazz, Class<A> annotationType) {
        AssertUtil.notNull(annotationType, "param annotationType must not be null");
        AssertUtil.notNull(clazz, "param clazz must not be null");
        return clazz.getDeclaredAnnotation(annotationType);
    }

    public static <A extends Annotation> boolean hasAnnotation(Class<?> clazz, Class<A> annotationType) {
        return clazz.isAnnotationPresent(annotationType);
    }

    public static <A extends Annotation> boolean hasAnnotation(Field field, Class<A> annotationType) {
        return field.isAnnotationPresent(annotationType);
    }
}
