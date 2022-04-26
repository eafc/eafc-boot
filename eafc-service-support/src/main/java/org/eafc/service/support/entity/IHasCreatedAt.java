package org.eafc.service.support.entity;

import java.util.Date;

/**
 * 创建时间
 *
 * @author liuxx
 * @date 2022/4/26
 */
public interface IHasCreatedAt {

    /**
     * 获取创建时间
     *
     * @return createdAt
     */
    Date getCreatedAt();

    /**
     * 设置创建时间
     *
     * @param createdAt createdAt
     */
    void setCreatedAt(Date createdAt);
}
