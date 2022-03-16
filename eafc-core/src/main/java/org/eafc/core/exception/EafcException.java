package org.eafc.core.exception;

/**
 * @author liuxx
 * @date 2022/3/16
 */
public class EafcException extends RuntimeException {

    protected final String errorCode;

    public EafcException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public EafcException(String errorCode, String message, Throwable ex) {
        super(message, ex);
        this.errorCode = errorCode;
    }

    public EafcException(IErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMessage());
    }

    public EafcException(IErrorCode errorCode, Throwable ex) {
        this(errorCode.getCode(), errorCode.getMessage(), ex);
    }

    /**
     * 获取异常对应的错误码
     *
     * @return 错误码
     */
    public String getErrorCode() {
        return this.errorCode;
    }
}
