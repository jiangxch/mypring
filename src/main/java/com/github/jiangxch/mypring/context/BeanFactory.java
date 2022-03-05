package com.github.jiangxch.mypring.context;

import java.util.*;

/**
 * @author: jiangxch
 * @date: 2022/3/2 11:53 PM
 */
public class BeanFactory {
    private Map<String, Object> beanNameObjectMap = new HashMap<>();
    private Map<String, Object> beanNameEarlyObjectMap = new HashMap<>();
    private Map<String, Object> beanNameEarlyAopReferenceMap = new HashMap<>();

    public BeanFactory() {

    }

    public <T> T getBean(Class<T> requiredType) {
        return null;
    }
}
