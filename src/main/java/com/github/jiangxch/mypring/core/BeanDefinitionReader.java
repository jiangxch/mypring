package com.github.jiangxch.mypring.core;

import com.github.jiangxch.mypring.config.annotation.Component;
import com.github.jiangxch.mypring.config.annotation.ComponentScan;
import com.github.jiangxch.mypring.util.ClassUtil;
import com.github.jiangxch.mypring.util.StringUtil;
import com.github.jiangxch.mypring.util.reflection.AnnotationUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: jiangxch
 * @date: 2022/3/5 2:44 PM
 */
public class BeanDefinitionReader {
    public static final Character COMMA = ',';

    private final ResourceResolve resourceResolve;

    public BeanDefinitionReader(ResourceResolve resourceResolve) {
        this.resourceResolve = resourceResolve;
    }

    public List<BeanDefinition> readBeanDefinitions(Class<?> classWithComponentScan) {
        ComponentScan componentScan = AnnotationUtil.findAnnotation(classWithComponentScan, ComponentScan.class);
        if (componentScan == null) {
            throw new IllegalArgumentException("the class=" + classWithComponentScan.getName()
                    + "is not place @ComponentScant annotation ");
        }
        return readBeanDefinitions(componentScan.value());
    }

    public List<BeanDefinition> readBeanDefinitions(String packageNamesWithCommaSplit) {
        Set<BeanDefinition> res = new HashSet<>();
        String[] packageNames = StringUtil.delimitedStringToStringArray(packageNamesWithCommaSplit, COMMA);
        try {
            for (String packageName : packageNames) {
                URL[] classFileResources = this.resourceResolve.getResources(packageName);
                ClassLoader cl = Thread.currentThread().getContextClassLoader();
                for (URL classFileResource : classFileResources) {
                    String className = ClassUtil.convertUrlResourcePathToClassName(classFileResource);
                    Class<?> clazz = cl.loadClass(className);
                    Component component = AnnotationUtil.findAnnotation(clazz, Component.class);
                    if (component != null) {
                        BeanDefinition beanDefinition = convertClassToBeanDefinition(clazz);
                        res.add(beanDefinition);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(res);
    }

    /**
     * 将class转为beanDefinition
     * @param clazz
     * @return beanDefinition，如果class没有使用@Component注解，则返回null
     */
    private static BeanDefinition convertClassToBeanDefinition(Class<?> clazz) {
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClass(clazz);
        beanDefinition.setBeanName(BeanDefinition.getBeanNameByClass(clazz));
        return beanDefinition;
    }
}
