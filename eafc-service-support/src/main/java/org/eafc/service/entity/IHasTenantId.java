package org.eafc.service.entity;

import java.io.Serializable;

/**
 * 租户id
 *
 * @author liuxx
 * @since 2022/4/26
 */
public interface IHasTenantId<TKey extends Serializable>  {

    /**
     * 获取租户id
     *
     * @return id
     */
    TKey getTenantId();

    /**
     * 设置租户id
     */
    void setTenantId();
}
