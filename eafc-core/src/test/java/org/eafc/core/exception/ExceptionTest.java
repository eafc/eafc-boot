package org.eafc.core.exception;

import org.eafc.core.Constant;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author kayn.liu
 * @date 2022/3/16
 */
public class ExceptionTest {

    private static final String MESSAGE = "test message.";

    @Test
    public void testUserFriendlyException() {
        UserFriendlyException defaultException = new UserFriendlyException();
        Assert.assertEquals(ErrorCode.A0001.getCode(), defaultException.getErrorCode());
        Assert.assertEquals(ErrorCode.A0001.getMessage(), defaultException.getMessage());

        UserFriendlyException exception = new UserFriendlyException(MESSAGE);
        Assert.assertEquals(ErrorCode.A0001.getCode(), exception.getErrorCode());
        Assert.assertEquals(MESSAGE, exception.getMessage());


        Throwable causeException = new RpcException(200);

        UserFriendlyException exception1 = new UserFriendlyException(ErrorCode.A0101, causeException);
        Assert.assertEquals(ErrorCode.A0101.getCode(), exception1.getErrorCode());
        Assert.assertEquals(ErrorCode.A0101.getMessage(), exception1.getMessage());
        Assert.assertEquals(causeException, exception1.getCause());

        UserFriendlyException exception2 = new UserFriendlyException(ErrorCode.A0101.getCode(), MESSAGE, causeException);
        Assert.assertEquals(ErrorCode.A0101.getCode(), exception2.getErrorCode());
        Assert.assertEquals(MESSAGE, exception2.getMessage());
        Assert.assertEquals(causeException, exception2.getCause());
    }

    @Test
    public void testUserArgumentException() {
        UserArgumentException defaultException = new UserArgumentException();
        Assert.assertEquals(ErrorCode.A0101.getCode(), defaultException.getErrorCode());
        Assert.assertEquals(ErrorCode.A0101.getMessage(), defaultException.getMessage());

        UserArgumentException exception = new UserArgumentException(MESSAGE);
        Assert.assertEquals(ErrorCode.A0101.getCode(), exception.getErrorCode());
        Assert.assertEquals(MESSAGE, exception.getMessage());
    }

    @Test
    public void testRpcException() {
        RpcException rpcException = new RpcException();
        Assert.assertEquals(ErrorCode.C0001.getCode(), rpcException.getErrorCode());
        Assert.assertEquals(Constant.EMPTY, rpcException.getMessage());

        RpcException rpcException1 = new RpcException(500);
        Assert.assertEquals(500, rpcException1.getRpcCode());
    }
}
