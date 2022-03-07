package com.github.jiangxch.mypring.test.context;

import com.github.jiangxch.mypring.config.annotation.ComponentScan;
import com.github.jiangxch.mypring.context.AnnotationConfigApplicationContext;
import com.github.jiangxch.mypring.test.context.service.HelloService;
import org.junit.Test;

import java.util.Objects;

/**
 * @author: jiangxch
 * @date: 2022/3/8 12:12 AM
 */
public class AnnotationConfigApplicationContextTest {
    @Test
    public void getBean() {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ApplicationConfig.class);
        applicationContext.init();
        HelloService helloService = applicationContext.getBean(HelloService.class);
        String res = helloService.sayHello("jiangxch");
        assert Objects.equals(res, "Hello,jiangxch");
    }

    @ComponentScan("com.github.jiangxch.mypring.test.context.service")
    public static class ApplicationConfig {

    }
}
