package org.eafc.spring.utils;

import org.eafc.spring.beans.Application;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author liuxx
 * @date 2022/4/5
 */
public class EnvironmentUtilsTest {

    private static final String TEST_KEY = "eafc.since";
    private static final String TEST_VALUE = "2022";
    private static final String ERROR_KEY = "eafc.error";
    private static final String DEFAULT_VALUE = "default";

    @Test
    public void test() {
        new AnnotationConfigApplicationContext(Application.class);

        Environment environment = EnvironmentUtils.getEnvironment();
        Assert.assertNotNull(environment);

        Assert.assertEquals(TEST_VALUE, EnvironmentUtils.getProperty(TEST_KEY));

        Assert.assertNull(EnvironmentUtils.getProperty(ERROR_KEY));
        Assert.assertEquals(DEFAULT_VALUE, EnvironmentUtils.getProperty(ERROR_KEY, DEFAULT_VALUE));

        Integer intValue = EnvironmentUtils.getProperty(TEST_KEY, Integer.class);
        Assert.assertEquals(2022, (int) intValue);

        Integer intError = EnvironmentUtils.getProperty(ERROR_KEY, Integer.class);
        Assert.assertNull(intError);

        Integer intDefault = EnvironmentUtils.getProperty(ERROR_KEY, Integer.class, 2000);
        Assert.assertEquals(2000, (int) intDefault);
    }
}
