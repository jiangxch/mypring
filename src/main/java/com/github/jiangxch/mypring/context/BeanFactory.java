package com.github.jiangxch.mypring.context;

import com.github.jiangxch.mypring.api.BeanPostProcessor;
import com.github.jiangxch.mypring.core.BeanDefinition;
import com.github.jiangxch.mypring.util.reflection.ReflectionUtil;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: jiangxch
 * @date: 2022/3/2 11:53 PM
 */
public class BeanFactory {
    private final Map<String, Object> beanNameObjectMap = new HashMap<>();
    private final Map<String, Object> beanNameEarlyObjectMap = new HashMap<>();
    private final Map<String, Object> beanNameEarlyWrapObjectMap = new HashMap<>();

    public BeanFactory() {
    }


    public void load(List<BeanDefinition> beanDefinitions) {
        newEarlyObjects(beanDefinitions);

        wrapEarlyObject(beanDefinitions);

        populateProperties(beanDefinitions);

        clearEarlyObject(beanDefinitions);
    }

    private void clearEarlyObject(List<BeanDefinition> beanDefinitions) {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            String beanName = beanDefinition.getBeanName();
            Object wrapObject = this.beanNameEarlyWrapObjectMap.get(beanName);
            if (wrapObject != null) {
                this.beanNameObjectMap.put(beanName, wrapObject);
            } else {
                this.beanNameObjectMap.put(beanName, this.beanNameEarlyObjectMap.get(beanName));
            }
            this.beanNameEarlyObjectMap.remove(beanName);
            this.beanNameEarlyWrapObjectMap.remove(beanName);
        }
    }

    public <T> T getBean(Class<T> requiredType) {
        String beanName = BeanDefinition.getBeanNameByClass(requiredType);
        Object bean = this.beanNameObjectMap.get(beanName);
        if (bean == null) {
            throw new IllegalArgumentException("can't find bean with class=" + requiredType.getName());
        }
        return requiredType.cast(bean);
    }

    private void wrapEarlyObject(List<BeanDefinition> beanDefinitions) {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            String beanName = beanDefinition.getBeanName();
            Object earlyBean = this.beanNameEarlyObjectMap.get(beanName);
            if (earlyBean instanceof BeanPostProcessor) {
                BeanPostProcessor postProcessor = (BeanPostProcessor) earlyBean;
                Object wrapBean = postProcessor.wrapEarlyObject(earlyBean, beanName);
                this.beanNameEarlyWrapObjectMap.put(beanName, wrapBean);
            }
        }
    }

    private void populateProperties(List<BeanDefinition> beanDefinitions) {
        try {
            for (BeanDefinition beanDefinition : beanDefinitions) {
                String beanName = beanDefinition.getBeanName();
                Object bean = this.beanNameEarlyObjectMap.get(beanName);
                Class<?> beanClass = beanDefinition.getBeanClass();
                List<Field> autowireFields = ReflectionUtil.findFiledByAnnotation(beanClass, Resource.class);
                for (Field autowireField : autowireFields) {
                    String autowireBeanName = BeanDefinition.getBeanNameByClass(autowireField.getType());
                    Object autowireBean = this.beanNameEarlyObjectMap.get(autowireBeanName);
                    if (autowireBean == null) {
                        throw new RuntimeException("can't find bean with class=" + autowireField.getType().getName());
                    }
                    boolean accessible = autowireField.isAccessible();
                    autowireField.setAccessible(true);
                    autowireField.set(bean, autowireBean);
                    autowireField.setAccessible(accessible);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void newEarlyObjects(List<BeanDefinition> beanDefinitions) {
        try {
            for (BeanDefinition beanDefinition : beanDefinitions) {
                String beanName = beanDefinition.getBeanName();
                Object obj = beanDefinition.getBeanClass().newInstance();
                this.beanNameEarlyObjectMap.put(beanName, obj);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
