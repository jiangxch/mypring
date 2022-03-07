package com.github.jiangxch.mypring.util.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: jiangxch
 * @date: 2022/3/6 1:15 AM
 */
public class ReflectionUtil {
    /**
     * 查找class上的所有包含该注解的字段，不包含继承的字段
     * @param clazz
     * @param annotation
     * @return
     */
    public static List<Field> findFiledByAnnotation(Class<?> clazz, Class<? extends Annotation> annotation) {
        List<Field> res = new ArrayList<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (AnnotationUtil.hasAnnotation(declaredField, annotation)) {
                res.add(declaredField);
            }
        }
        return res;
    }
}
