package org.eafc.spring.beans.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author liuxx
 * @date 2022/3/27
 */
public class BeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) {
        System.out.println("Log before spring aop.");
    }
}
