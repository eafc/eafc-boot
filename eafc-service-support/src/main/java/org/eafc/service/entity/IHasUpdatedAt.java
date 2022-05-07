package org.eafc.service.entity;

import java.util.Date;

/**
 * 修改时间
 *
 * @author liuxx
 * @date 2022/4/26
 *
 * Model-最后修改时间接口
 */
public interface IHasUpdatedAt {

    /**
     * 获取修改时间
     * @return updatedAt
     */
    Date getUpdatedAt();

    /**
     * 设置修改时间
     * @param updatedAt updatedAt
     */
    void setUpdatedAt(Date updatedAt);
}
