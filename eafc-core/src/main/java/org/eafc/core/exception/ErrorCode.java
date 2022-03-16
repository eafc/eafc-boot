package org.eafc.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * 错误码分为一级宏观错误码、二级宏观错误码、三级宏观错误码。
 * 在无法更加具体确定的错误场景中，可以直接使用一级宏观错误码，分别是：A0001（用户端错误）、B0001（系统执行出错）、C0001（调用第三方服务出错）。
 *
 * @author liuxx
 * @since 2022/3/16
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode implements IErrorCode {

    /**
     * 成功
     */
    SUCCESS("00000", "一切OK"),

    /**
     * 用户类异常
     */
    A0001("A0001", "用户端错误"),
    A0101("A0101", "用户参数校验失败"),

    /**
     * 系统类异常
     */
    B0001("B0001", "系统执行出错"),

    /**
     * 三方服务异常
     */
    C0001("C0001", "调用第三方服务出错");


    private final String code;

    private final String message;
}
