package com.github.jiangxch.mypring.core;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: jiangxch
 * @date: 2022/3/2 11:53 PM
 */
@Data
@EqualsAndHashCode
public class BeanDefinition {
    private Class<?> beanClass;
    private String beanName;

    /**
     * 生成 beanName
     *
     * @param clazz
     * @return class.getSimpleName 首字母小写
     */
    public static String getBeanNameByClass(Class<?> clazz) {
        String simpleName = clazz.getSimpleName();
        char[] chars = simpleName.toCharArray();
        if (chars.length > 0) {
            chars[0] = (char) (chars[0] + 32);
        }
        return new String(chars);
    }
}
