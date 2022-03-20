package org.eafc.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @author liuxx
 * @date 2022/3/20
 */
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;

    private static final Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);

    /**
     * 获取 Spring上下文 {@link ApplicationContext}
     *
     * @return {@link ApplicationContext}
     */
    public static ApplicationContext getApplicationContext() {
        Assert.notNull(applicationContext, "SpringContextHolder未注册到Spring容器中");

        return applicationContext;
    }

    /**
     * 根据beanName获取Bean实例
     *
     * @param beanName beanName
     * @param <T>      泛型
     * @return Bean实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        return (T) getApplicationContext().getBean(beanName);
    }

    /**
     * 根据类型获取Bean实例
     *
     * @param beanType beanType
     * @param <T>      泛型
     * @return Bean实例
     */
    public static <T> T getBean(Class<T> beanType) {
        return getApplicationContext().getBean(beanType);
    }

    /**
     * 根据类型获取Bean的所有实例
     *
     * @param beanType beanType
     * @param <T>      泛型
     * @return Bean实例集合
     */
    public static <T> Map<String, T> getBeansOfType(@Nullable Class<T> beanType) {
        return getApplicationContext().getBeansOfType(beanType);
    }

    /**
     * 清除SpringContextHolder中的ApplicationContext
     */
    public static void clearHolder() {
        applicationContext = null;
        logger.info("SpringContextHolder clear.");
    }

    /**
     * 实现ApplicationContextAware接口, 注入Context到静态变量中.
     */
    @Override
    public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
        logger.info("SpringContextHolder register success.");
    }

    /**
     * 实现DisposableBean接口, 在Context关闭时清理静态变量.
     */
    @Override
    public void destroy() {
        SpringContextHolder.clearHolder();
    }
}
