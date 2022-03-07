package com.github.jiangxch.mypring.test.core;

import com.github.jiangxch.mypring.core.BeanDefinition;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author: jiangxch
 * @date: 2022/3/7 12:04 AM
 */
public class BeanDefinitionTest {
    @Test
    public void testGetBeanNameByClass() {
        Class<?> clazz = BeanDefinitionTest.class;
        String beanName = BeanDefinition.getBeanNameByClass(clazz);
        Assert.assertEquals("beanDefinitionTest", beanName);
    }
}
