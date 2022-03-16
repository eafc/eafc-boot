package org.eafc.core.exception;

/**
 * 用户友好的异常，错误消息将传递至调用方
 *
 * @author liuxx
 * @date 2022/3/16
 */
public class UserFriendlyException extends EafcException {

    public UserFriendlyException() {
        this(ErrorCode.A0001);
    }

    public UserFriendlyException(String message) {
        this(ErrorCode.A0001.getCode(), message);
    }

    public UserFriendlyException(String errorCode, String message) {
        super(errorCode, message);
    }

    public UserFriendlyException(String errorCode, String message, Throwable ex) {
        super(errorCode, message, ex);
    }

    public UserFriendlyException(IErrorCode errorCode) {
        super(errorCode);
    }

    public UserFriendlyException(IErrorCode errorCode, Throwable ex) {
        super(errorCode, ex);
    }
}
