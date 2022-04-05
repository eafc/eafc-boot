package org.eafc.spring.utils;

import org.eafc.spring.SpringContextHolder;
import org.springframework.core.env.Environment;

/**
 * @author liuxx
 * @date 2022/4/5
 */
public final class EnvironmentUtils {

    private EnvironmentUtils() {
    }

    /**
     * Return the spring environment
     *
     * @return {@link Environment}
     */
    public static Environment getEnvironment() {
        return SpringContextHolder.getBean(Environment.class);
    }


    /**
     * Return the property value associated with the given key,
     * or {@code null} if the key cannot be resolved.
     *
     * @param key the property name to resolve
     * @see #getProperty(String, String)
     * @see #getProperty(String, Class)
     */
    public static String getProperty(String key) {
        return getEnvironment().getProperty(key);
    }

    /**
     * Return the property value associated with the given key, or
     * {@code defaultValue} if the key cannot be resolved.
     *
     * @param key          the property name to resolve
     * @param defaultValue the default value to return if no value is found
     * @see #getProperty(String, Class)
     */
    public static String getProperty(String key, String defaultValue) {
        return getEnvironment().getProperty(key, defaultValue);
    }

    /**
     * Return the property value associated with the given key,
     * or {@code null} if the key cannot be resolved.
     *
     * @param key        the property name to resolve
     * @param targetType the expected type of the property value
     */
    public static <T> T getProperty(String key, Class<T> targetType) {
        return getEnvironment().getProperty(key, targetType);
    }

    /**
     * Return the property value associated with the given key,
     * or {@code defaultValue} if the key cannot be resolved.
     *
     * @param key          the property name to resolve
     * @param targetType   the expected type of the property value
     * @param defaultValue the default value to return if no value is found
     */
    public static <T> T getProperty(String key, Class<T> targetType, T defaultValue) {
        return getEnvironment().getProperty(key, targetType, defaultValue);
    }
}
