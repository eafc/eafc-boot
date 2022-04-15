package org.eafc.web.support.advice;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.eafc.core.Result;
import org.eafc.core.exception.ErrorCode;
import org.eafc.core.exception.RpcException;
import org.eafc.core.exception.UserArgumentException;
import org.eafc.core.exception.UserFriendlyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Optional;

/**
 * @author liuxx
 * @date 2022/4/15
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionAdvice {

    private final static String DEFAULT_ERROR_MASSAGE = "系统走神了,请稍候再试.";
    private final static String ARGUMENT_ERROR_MESSAGE = "非法的参数";

    /**
     * 校验类异常处理
     *
     * @param response response
     * @param ex       {@link BindException}
     * @return {@link Result}
     */
    @ResponseBody
    @ExceptionHandler(BindException.class)
    public Result<Void> handle(HttpServletResponse response, BindException ex) {
        String message = ARGUMENT_ERROR_MESSAGE;
        FieldError fieldError = ex.getBindingResult().getFieldError();
        if (fieldError != null && StringUtils.isBlank(fieldError.getDefaultMessage())) {
            String defaultMessage = fieldError.getDefaultMessage();
            if (StringUtils.contains(defaultMessage, IllegalArgumentException.class.getName())) {
                message = defaultMessage.substring(defaultMessage.lastIndexOf(":") + 2);
            }
        }

        log.warn(message, ex);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return Result.fail(ErrorCode.A0101.getCode(), message);
    }

    /**
     * 校验类异常处理
     *
     * @param response response
     * @param ex       {@link MethodArgumentNotValidException}
     * @return {@link Result}
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handle(HttpServletResponse response, MethodArgumentNotValidException ex) {

        FieldError fieldError = ex.getBindingResult().getFieldError();
        String message = fieldError == null ? ARGUMENT_ERROR_MESSAGE : fieldError.getDefaultMessage();

        log.warn(message, ex);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return Result.fail(ErrorCode.A0101.getCode(), message);
    }

    /**
     * 校验类异常处理
     *
     * @param response response
     * @param ex       {@link ConstraintViolationException}
     * @return {@link Result}
     */
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<Void> handle(HttpServletResponse response, ConstraintViolationException ex) {

        Optional<ConstraintViolation<?>> error = ex.getConstraintViolations().stream().findFirst();
        String message = error.isPresent() ? error.get().getMessage() : ARGUMENT_ERROR_MESSAGE;

        log.warn(message, ex);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return Result.fail(ErrorCode.A0101.getCode(), message);
    }

    /**
     * 非法参数异常处理
     *
     * @param response response
     * @param ex       {@link IllegalArgumentException}
     * @return {@link Result}
     */
    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<Void> handle(HttpServletResponse response, IllegalArgumentException ex) {

        log.warn(ex.getMessage(), ex);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return Result.fail(ErrorCode.A0101.getCode(), ARGUMENT_ERROR_MESSAGE);
    }

    /**
     * 用户友好的参数异常处理
     *
     * @param response response
     * @param ex       {@link UserArgumentException}
     * @return {@link Result}
     */
    @ResponseBody
    @ExceptionHandler(UserArgumentException.class)
    public Result<Void> handle(HttpServletResponse response, UserArgumentException ex) {
        log.warn(ex.getMessage(), ex);

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return Result.fail(ex);
    }

    /**
     * 用户友好的异常处理
     *
     * @param response response
     * @param ex       {@link UserFriendlyException}
     * @return {@link Result}
     */
    @ResponseBody
    @ExceptionHandler(UserFriendlyException.class)
    public Result<Void> handle(HttpServletResponse response, UserFriendlyException ex) {
        log.warn(ex.getMessage(), ex);

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return Result.fail(ex);
    }

    /**
     * Feign调用异常处理
     *
     * @param response response
     * @param ex       {@link RpcException}
     * @return {@link Result}
     */
    @ResponseBody
    @ExceptionHandler(RpcException.class)
    public Result<Void> handle(HttpServletResponse response, RpcException ex) {
        log.error(ex.getMessage(), ex);

        response.setStatus(ex.getRpcCode());
        return Result.fail(ex);
    }

    /**
     * 其他异常处理
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<Void> handle(HttpServletResponse response, Exception ex) {
        log.error(ex.getMessage(), ex);

        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return Result.fail(ErrorCode.B0001.getCode(), DEFAULT_ERROR_MASSAGE);
    }
}
