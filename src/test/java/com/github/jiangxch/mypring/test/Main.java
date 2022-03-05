package com.github.jiangxch.mypring.test;

import com.github.jiangxch.mypring.context.AnnotationConfigApplicationContext;
import com.github.jiangxch.mypring.test.service.HelloService;
import org.junit.Test;

import java.util.Objects;

/**
 * @author: jiangxch
 * @date: 2022/3/2 10:37 PM
 */
public class Main {
    @Test
    public void main() {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext("com.github.jiangxch.mypring.test.service");
        HelloService helloService = applicationContext.getBean(HelloService.class);
        String res = helloService.sayHello("jiangxch");
        assert Objects.equals(res, "Hello,jiangxch");
    }
}
