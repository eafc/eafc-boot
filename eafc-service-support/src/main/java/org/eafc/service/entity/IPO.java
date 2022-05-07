package org.eafc.service.entity;

import java.io.Serializable;

/**
 * @author liuxx
 * @date 2022/4/26
 */
public interface IPO<TKey extends Serializable> extends Serializable {

    /**
     * 获取id
     * @return id
     */
    TKey getId();

    /**
     * 设置id
     * @param id id
     */
    void setId(TKey id);
}
