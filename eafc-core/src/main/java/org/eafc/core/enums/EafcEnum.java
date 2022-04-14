package org.eafc.core.enums;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author liuxx
 * @date 2022/3/16
 */
public interface EafcEnum<T extends Serializable> {

    /**
     * 枚举名，用于展示
     *
     * @return 枚举名
     */
    String getName();

    /**
     * 枚举值
     *
     * @return value
     */
    T getValue();

    /**
     * 值比较
     *
     * @param value 输入的 value
     * @return 是否相等
     */
    default boolean equalsValue(T value) {
        return Objects.equals(value, this.getValue());
    }
}
