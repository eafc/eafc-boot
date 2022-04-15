package org.eafc.web.support.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.eafc.spring.SpringContextHolder;

/**
 * @author liuxx
 * @date 2022/4/15
 */
@Slf4j
public class JsonUtils {

    private static final ObjectMapper DEFAULT_OBJECT_MAPPER = new ObjectMapper();

    public static String toJson(Object obj) {
        try {
            return getObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            log.error("format json error.", ex);
            return obj.toString();
        }
    }

    public static <T> T parse(String json, Class<T> clazz) {
        try {
            return getObjectMapper().readValue(json, clazz);
        } catch (JsonProcessingException ex) {
            log.error("parse json error, json:{},clazz:{}", json, clazz.getName(), ex);
            return null;
        }
    }

    public static <T> T parse(String json, TypeReference<T> typeRef) {
        try {
            return getObjectMapper().readValue(json, typeRef);
        } catch (JsonProcessingException ex) {
            log.error("parse json error, json:{},clazz:{}", json, typeRef.getType().getTypeName(), ex);
            return null;
        }
    }

    private static ObjectMapper getObjectMapper() {
        try {
            return SpringContextHolder.getBean(ObjectMapper.class);
        } catch (Exception ex) {
            log.error("get ObjectMapper from spring context error.", ex);
            return DEFAULT_OBJECT_MAPPER;
        }
    }
}
