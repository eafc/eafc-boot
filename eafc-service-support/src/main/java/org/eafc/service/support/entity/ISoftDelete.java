package org.eafc.service.support.entity;

/**
 * 逻辑删除标识
 *
 * @author liuxx
 * @date 2022/4/26
 *
 * Model-软删除接口
 */
public interface ISoftDelete {

    /**
     * 获取是否软删除
     * @return delFlag
     */
    Boolean getDelFlag();

    /**
     * 设置为删除
     * @param delFlag delFlag
     */
    void setDelFlag(Boolean delFlag);
}
