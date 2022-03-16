package org.eafc.core.exception;

/**
 * 用户友好的参数校验异常，错误消息将传递至调用方
 *
 * @author liuxx
 * @since 2022/3/16
 */
public class UserArgumentException extends EafcException {
    public UserArgumentException() {
        this(ErrorCode.A0101);
    }

    public UserArgumentException(String message) {
        this(ErrorCode.A0101.getCode(), message);
    }

    public UserArgumentException(String errorCode, String message) {
        super(errorCode, message);
    }

    public UserArgumentException(IErrorCode errorCode) {
        super(errorCode);
    }
}
