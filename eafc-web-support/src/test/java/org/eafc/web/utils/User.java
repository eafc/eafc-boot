package org.eafc.web.utils;

import lombok.Data;

/**
 * @author liuxx
 * @date 2022/4/15
 */
@Data
public class User {

    private String name;

    private Integer age;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
