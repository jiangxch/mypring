package com.github.jiangxch.mypring.api;

public interface BeanPostProcessor {

	default Object postProcessBeforeInitialization(Object bean, String beanName)  {
		return bean;
	}

	default Object postProcessAfterInitialization(Object bean, String beanName)  {
		return bean;
	}

}
