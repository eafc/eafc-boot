package org.eafc.service.entity;

/**
 * 修改人
 *
 * @author liuxx
 * @date 2022/4/26
 */
public interface IHasUpdatedBy {

    /**
     * 获取修改者
     *
     * @return 修改者
     */
    String getUpdatedBy();

    /**
     * 设置修改者
     * @param updatedBy 修改者
     */
    void setUpdatedBy(String updatedBy);
}
