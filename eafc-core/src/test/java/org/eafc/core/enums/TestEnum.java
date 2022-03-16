package org.eafc.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author liuxx
 * @date 2022/3/16
 */
@Getter
@RequiredArgsConstructor
public enum TestEnum implements EafcEnum<String> {

    SUCCESS("成功", "success"),

    FAIL("失败", "fail");

    private final String name;
    private final String value;
}
