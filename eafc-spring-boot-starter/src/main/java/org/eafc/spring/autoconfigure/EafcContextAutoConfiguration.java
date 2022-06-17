package org.eafc.spring.autoconfigure;

import org.eafc.spring.SpringContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuxx
 * @date 2022/6/17
 */
@Configuration(proxyBeanMethods = false)
public class EafcContextAutoConfiguration {

    /**
     * 注册 {@link SpringContextHolder}
     */
    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}
