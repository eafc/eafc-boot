package org.eafc.core;

import lombok.Data;
import org.eafc.core.exception.EafcException;
import org.eafc.core.exception.ErrorCode;

import java.io.Serializable;

/**
 * @author liuxx
 * @date 2022/4/15
 */
@Data
public class Result<T> implements Serializable {

    private String code;
    private String message;
    private Long timestamp;
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    public static Result<Void> fail(String errorCode, String message) {
        return new Result<>(errorCode, message);
    }

    public static Result<Void> fail(EafcException exception) {
        String message = exception.getMessage();

        return new Result<>(exception.getErrorCode(), message);
    }

    public Result() {
        this.timestamp = System.currentTimeMillis();
    }

    public Result(T result) {
        this(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), result);
    }

    public Result(String code, String message) {
        this(code, message, null);
    }

    public Result(String code, String message, T data) {
        this();
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
