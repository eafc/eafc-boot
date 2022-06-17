package org.eafc.spring.autoconfigure.web;

import org.eafc.web.advice.GlobalExceptionAdvice;
import org.eafc.web.advice.JsonWrapperAdvice;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuxx
 * @date 2022/6/17
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(WebProperties.class)
@ConditionalOnClass(name = "org.eafc.web.advice.GlobalExceptionAdvice")
public class WebAutoConfiguration {

    private static final String PREFIX = "eafc.web.";

    private final WebProperties webProperties;

    public WebAutoConfiguration(WebProperties webProperties) {
        this.webProperties = webProperties;
    }

    /**
     * 注册 统一的响应结果处理组件
     */
    @Bean
    @ConditionalOnProperty(value = PREFIX + "json-wrapper-enabled", havingValue = "true", matchIfMissing = true)
    public JsonWrapperAdvice restJsonWrapperAdvice() {
        return new JsonWrapperAdvice(this.webProperties.getBasePackages());
    }

    /**
     * 注册 统一的异常处理组件
     */
    @Bean
    @ConditionalOnProperty(value = PREFIX + "global-exception-enabled", havingValue = "true", matchIfMissing = true)
    public GlobalExceptionAdvice globalExceptionAdvice() {
        return new GlobalExceptionAdvice();
    }
}
