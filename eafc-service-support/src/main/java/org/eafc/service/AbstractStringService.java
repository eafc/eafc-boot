package org.eafc.service;

import org.apache.commons.lang3.StringUtils;
import org.eafc.service.entity.IPO;
import org.eafc.service.mapper.AbstractMapper;

/**
 * @author liuxx
 * @date 2022/5/7
 */
public abstract class AbstractStringService<M extends AbstractMapper<T>,T extends IPO<String>> extends AbstractService<M,T,String> {

    @Override
    protected boolean isEmptyKey(String id) {
        return StringUtils.isBlank(id);
    }
}
