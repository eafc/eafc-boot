package org.eafc.core.enums;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * @author liuxx
 * @date 2022/3/16
 */
public class EafcEnumTest {

    @Test
    public void testEnumRegistry() {
        EafcEnumRegistry.register(TestEnum.class);

        Assert.assertTrue(EafcEnumRegistry.contains(TestEnum.class));

        EafcEnum<?> eafcEnum = EafcEnumRegistry.getEnum(TestEnum.class, "success");

        Assert.assertEquals(TestEnum.SUCCESS.getValue(), eafcEnum.getValue());
        Assert.assertEquals(TestEnum.SUCCESS.getName(), eafcEnum.getName());
        Assert.assertEquals(TestEnum.SUCCESS, eafcEnum);
        Assert.assertTrue(TestEnum.SUCCESS.equalsValue("success"));

        Map<Object, EafcEnum<?>> enumMap = EafcEnumRegistry.getEnums(TestEnum.class);
        Assert.assertEquals(TestEnum.values().length, enumMap.size());
    }
}
