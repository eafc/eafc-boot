package org.eafc.spring;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @author liuxx
 * @date 2022/3/20
 */
public class SpringContextHolderTest {

    private static final String BEAN_NAME = "springContextHolder";

    @Test
    public void testSpringContextHolder() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/context.xml");

        Assert.assertEquals(applicationContext, SpringContextHolder.getApplicationContext());
        Assert.assertNotNull(SpringContextHolder.getBean(BEAN_NAME));
        Assert.assertEquals(SpringContextHolder.getBean(BEAN_NAME), SpringContextHolder.getBean(SpringContextHolder.class));

        Map<String, SpringContextHolder> map = SpringContextHolder.getBeansOfType(SpringContextHolder.class);
        Assert.assertEquals(map, applicationContext.getBeansOfType(SpringContextHolder.class));
    }
}
