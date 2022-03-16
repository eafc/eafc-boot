package org.eafc.core.exception;

import org.eafc.core.Constant;

/**
 * 服务调用异常
 *
 * @author liuxx
 * @date 2022/3/16
 */
public class RpcException extends EafcException {

    /**
     * RPC服务端返回的Code，用于区分RPC调用异常，如果是Http调用，则对应HttpCode。
     */
    private final int rpcCode;

    public RpcException() {
        this(0);
    }

    public RpcException(int rpcCode) {
        this(rpcCode, Constant.EMPTY);
    }

    public RpcException(int rpcCode, String message) {
        this(rpcCode, ErrorCode.C0001.getCode(), message);
    }

    public RpcException(int rpcCode, String errorCode, String message) {
        super(errorCode, message);
        this.rpcCode = rpcCode;
    }

    public int getRpcCode() {
        return rpcCode;
    }
}
