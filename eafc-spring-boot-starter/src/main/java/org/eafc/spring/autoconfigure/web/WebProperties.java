package org.eafc.spring.autoconfigure.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author liuxx
 * @date 2022/6/17
 */
@Data
@ConfigurationProperties(prefix = "eafc.web")
public class WebProperties {

    /**
     * 包路径
     */
    private String[] basePackages = {};
    /**
     * 是否开启全局异常处理
     */
    private Boolean globalExceptionEnable = true;
    /**
     * 是否开启全局结果包装器
     */
    private Boolean jsonWrapperEnabled = true;
}
