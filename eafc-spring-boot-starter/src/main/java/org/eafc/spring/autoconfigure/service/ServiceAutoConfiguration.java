package org.eafc.spring.autoconfigure.service;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import org.eafc.service.entity.identifier.StringKeyGenerator;
import org.eafc.service.mapper.interceptor.AuditMetaObjectHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author liuxx
 * @date 2022/6/17
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(ServiceProperties.class)
@ConditionalOnClass(name = "org.eafc.service.AbstractService")
public class ServiceAutoConfiguration {

    private static final String PREFIX = "eafc.service.";


    /**
     * 注册 {@link AuditMetaObjectHandler}
     */
    @Bean
    @ConditionalOnProperty(value = PREFIX + "audit-meta-object", havingValue = "true", matchIfMissing = true)
    public AuditMetaObjectHandler auditMetaObjectHandler() {
        return new AuditMetaObjectHandler();
    }

    /**
     * 注册 {@link BlockAttackInnerInterceptor}
     */
    @Bean
    @ConditionalOnProperty(value = PREFIX + "block-attack", havingValue = "true", matchIfMissing = true)
    public BlockAttackInnerInterceptor blockAttackInnerInterceptor() {
        return new BlockAttackInnerInterceptor();
    }

    /**
     * 注册 {@link OptimisticLockerInnerInterceptor}
     */
    @Bean
    @ConditionalOnProperty(value = PREFIX + "optimistic-lock-check", havingValue = "true")
    public OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor() {
        return new OptimisticLockerInnerInterceptor();
    }

    @Bean
    @ConditionalOnBean(InnerInterceptor.class)
    public MybatisPlusInterceptor mybatisPlusInterceptor(ObjectProvider<List<InnerInterceptor>> interceptorProvider) {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptorProvider.ifAvailable(innerInterceptors -> innerInterceptors.forEach(interceptor::addInnerInterceptor));
        return interceptor;
    }

    /**
     * 注册 {@link StringKeyGenerator}
     */
    @Bean
    @ConditionalOnMissingBean(IdentifierGenerator.class)
    public IdentifierGenerator stringKeyGenerator() {
        return new StringKeyGenerator();
    }
}
