package org.eafc.service.entity;

/**
 * 创建人
 *
 * @author liuxx
 * @date 2022/4/26
 */
public interface IHasCreatedBy {

    /**
     * 获取创建者
     *
     * @return 创建者
     */
    String getCreatedBy();

    /**
     * 设置创建者
     * @param createdBy 创建者
     */
    void setCreatedBy(String createdBy);
}
