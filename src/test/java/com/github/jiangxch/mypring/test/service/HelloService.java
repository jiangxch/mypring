package com.github.jiangxch.mypring.test.service;

import com.github.jiangxch.mypring.config.annotation.Component;

/**
 * @author: jiangxch
 * @date: 2022/3/3 11:39 PM
 */
@Component
public class HelloService {
    public String sayHello(String name) {
        return "Hello," + name;
    }
}
