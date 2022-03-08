package com.github.jiangxch.mypring.api;

public interface BeanPostProcessor {
	/**
	 * 仅仅用于AOP接口，并且实现该类无法使用注入的对象，因为这些属性在该方法调用时候还未初始化
	 * @param bean 实例化的bean对象
	 * @param beanName bean 名称
	 * @return aop包装后的对象
	 */
	default Object wrapEarlyObject(Object bean, String beanName)  {
		return bean;
	}

	/**
	 * bean 初始化的hook接口, 通常用于扫描bean的信息或者对bean进行增强，如设置配置中心的属性
	 * @param bean 调用init初始化方法后的bean对象，不经过AOP包装
	 * @param beanName bean名称
	 */
	default void postProcessAfterInitialization(Object bean, String beanName) {
	}
}
