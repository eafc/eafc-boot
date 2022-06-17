package org.eafc.spring.autoconfigure.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author liuxx
 * @date 2022/6/17
 */
@Data
@ConfigurationProperties(prefix = "eafc.service")
public class ServiceProperties {
    /**
     * 是否开启 审计字段自动填充
     */
    private Boolean auditMetaObject = true;

    /**
     * 是否开启 防止全表更新与删除
     */
    private Boolean blockAttack = true;

    /**
     * 是否开启 乐观锁检测
     */
    private Boolean optimisticLockCheck;
}
