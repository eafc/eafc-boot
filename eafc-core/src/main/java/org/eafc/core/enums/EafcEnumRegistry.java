package org.eafc.core.enums;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author liuxx
 * @date 2022/3/16
 */
@Slf4j
public final class EafcEnumRegistry {

    private static final ConcurrentHashMap<Class<? extends EafcEnum<?>>, Map<Object, EafcEnum<?>>> FULL_ENUM_MAP = new ConcurrentHashMap<>();

    private EafcEnumRegistry() {
    }

    public static boolean contains(Class<? extends EafcEnum<?>> enumClass) {
        return FULL_ENUM_MAP.containsKey(enumClass);
    }

    @SuppressWarnings("unchecked")
    public static void register(Class<? extends EafcEnum<?>> enumClass) {
        if (enumClass == null || FULL_ENUM_MAP.containsKey(enumClass)) {
            return;
        }
        if (!EafcEnum.class.isAssignableFrom(enumClass)) {
            log.error("enum register error, enumClass:{}", enumClass.getName());
        }

        Map<Object, EafcEnum<?>> enumMap = Arrays.stream(enumClass.getEnumConstants())
                .map(EafcEnum.class::cast)
                .collect(Collectors.toMap(EafcEnum::getValue, p -> p, (p1, p2) -> p2));

        FULL_ENUM_MAP.put(enumClass, enumMap);
    }

    public static Map<Object, EafcEnum<?>> getEnums(Class<?> enumClass) {
        return FULL_ENUM_MAP.getOrDefault(enumClass, new HashMap<>(8));
    }

    public static EafcEnum<?> getEnum(Class<?> enumClass, Object value) {
        Map<Object, EafcEnum<?>> enumMap = getEnums(enumClass);
        return enumMap.get(value);
    }
}
