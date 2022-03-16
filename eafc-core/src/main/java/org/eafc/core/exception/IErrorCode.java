package org.eafc.core.exception;

/**
 * @author liuxx
 * @since 2022/3/16
 */
public interface IErrorCode {

    /**
     * 获取错误码
     *
     * @return 错误码
     */
    String getCode();

    /**
     * 获取错误消息
     *
     * @return 错误消息
     */
    String getMessage();
}
