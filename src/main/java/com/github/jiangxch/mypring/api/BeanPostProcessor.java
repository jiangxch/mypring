package com.github.jiangxch.mypring.api;

public interface BeanPostProcessor {
	default Object wrapEarlyObject(Object bean, String beanName)  {
		return bean;
	}
}
