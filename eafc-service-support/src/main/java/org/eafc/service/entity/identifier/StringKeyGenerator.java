package org.eafc.service.entity.identifier;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

/**
 * @author liuxx
 * @since 2022/6/17
 */
public class StringKeyGenerator implements IdentifierGenerator {
    @Override
    public Number nextId(Object entity) {
        return null;
    }

    @Override
    public String nextUUID(Object entity) {
        return UUIDHexGenerator.generate();
    }
}
