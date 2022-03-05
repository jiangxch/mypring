package com.github.jiangxch.mypring.context;

/**
 * @author: jiangxch
 * @date: 2022/3/2 11:53 PM
 */
public class AnnotationConfigApplicationContext {
    private String basePackage;
    private BeanFactory beanFactory;

    public AnnotationConfigApplicationContext(String basePackage) {
        this.basePackage = basePackage;
    }

    public <T> T getBean(Class<T> requiredType) {
        return null;
    }
}
