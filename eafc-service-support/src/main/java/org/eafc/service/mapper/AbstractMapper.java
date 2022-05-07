package org.eafc.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.eafc.service.entity.IPO;

/**
 * @author liuxx
 * @date 2022/5/6
 */
public interface AbstractMapper<T extends IPO<?>> extends BaseMapper<T> {
}
