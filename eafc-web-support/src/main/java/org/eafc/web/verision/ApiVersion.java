package org.eafc.web.verision;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * 版本号
 *
 * @author liuxx
 * @since 2022/4/20
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {
    String value();
}
