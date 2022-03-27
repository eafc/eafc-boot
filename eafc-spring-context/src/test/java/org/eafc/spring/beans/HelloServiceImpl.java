package org.eafc.spring.beans;

/**
 * @author liuxx
 * @date 2022/3/27
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public void hello() {
        System.out.println("Hello, Spring!");
    }
}
