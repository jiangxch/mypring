package com.github.jiangxch.mypring.context;

import com.github.jiangxch.mypring.core.BeanDefinition;

import java.util.List;

/**
 * @author: jiangxch
 * @date: 2022/3/2 11:53 PM
 */
public class AnnotationConfigApplicationContext {
    private final BeanFactory beanFactory;
    private final BeanDefinitionReader beanDefinitionReader;
    private final Class<?> configurationClass;

    public AnnotationConfigApplicationContext(Class<?> configurationClass) {
        this.beanFactory = new BeanFactory();
        ResourceResolve resourceResolve = new ResourceResolve();
        this.beanDefinitionReader = new BeanDefinitionReader(resourceResolve);
        this.configurationClass = configurationClass;
    }

    public void init() {
        this.refresh();
    }

    private void refresh() {
        List<BeanDefinition> beanDefinitions = beanDefinitionReader.readBeanDefinitions(this.configurationClass);
        beanFactory.load(beanDefinitions);
    }

    public <T> T getBean(Class<T> requiredType) {
        return beanFactory.getBean(requiredType);
    }
}
