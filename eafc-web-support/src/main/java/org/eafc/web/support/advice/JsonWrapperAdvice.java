package org.eafc.web.support.advice;

import org.eafc.core.Result;
import org.eafc.web.support.utils.JsonUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author liuxx
 * @date 2022/4/14
 */
@RestControllerAdvice
public class JsonWrapperAdvice implements ResponseBodyAdvice<Object> {

    private final String[] scanWrapperPackages;

    public JsonWrapperAdvice(String... scanWrapperPackages) {
        this.scanWrapperPackages = scanWrapperPackages;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converter) {
        if (methodParameter.getMethodAnnotation(JsonWrapperIgnore.class) != null || methodParameter.getContainingClass().isAnnotationPresent(JsonWrapperIgnore.class)) {
            return false;
        }

        for (String wrapperPackage : this.scanWrapperPackages) {
            if (methodParameter.getDeclaringClass().getName().startsWith(wrapperPackage)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> converterType, ServerHttpRequest request, ServerHttpResponse response) {

        if (converterType.isAssignableFrom(StringHttpMessageConverter.class)) {
            return JsonUtils.toJson(Result.success(body));
        }
        if (body instanceof Result) {
            return body;
        }
        return Result.success(body);
    }
}
