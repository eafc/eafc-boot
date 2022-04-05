package org.eafc.spring.beans;

import org.eafc.spring.SpringContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author liuxx
 * @date 2022/4/5
 */
@Configuration
@PropertySource(value = "classpath:application.properties")
public class Application {


    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}
