package org.eafc.spring.utils;

import org.eafc.spring.beans.HelloService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author liuxx
 * @date 2022/3/27
 */
public class AopUtilsTest {

    @Test
    public void testAop() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/context.xml");

        HelloService origin = applicationContext.getBean("helloService", HelloService.class);
        origin.hello();

        HelloService proxy = applicationContext.getBean("helloServiceProxy", HelloService.class);
        proxy.hello();

        Assert.assertNotEquals(origin, proxy);
        Assert.assertEquals(origin, AopUtils.getTarget(proxy));
    }
}
