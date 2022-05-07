package org.eafc.service;

import org.eafc.service.entity.IPO;
import org.eafc.service.mapper.AbstractMapper;

import java.util.Objects;

/**
 * @author liuxx
 * @date 2022/5/7
 */
public abstract class AbstractLongService<M extends AbstractMapper<T>,T extends IPO<Long>> extends AbstractService<M,T,Long> {
    
    @Override
    protected boolean isEmptyKey(Long id) {
        return Objects.isNull(id) || id <= 0;
    }
}
