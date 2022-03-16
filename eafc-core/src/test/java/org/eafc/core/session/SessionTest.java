package org.eafc.core.session;

import org.junit.Assert;
import org.junit.Test;

import java.util.TimeZone;
import java.util.concurrent.CompletableFuture;

/**
 * @author liuxx
 * @date 2022/3/15
 */
public class SessionTest {

    private final static String COUNTRY = "cn";
    private final static String LANGUAGE = "zh-cn";
    private final static TimeZone TIMEZONE = TimeZone.getDefault();
    private final static String USER_ID = "user id";
    private final static String TENANT_ID = "tenant";
    private final static String ACCOUNT = "account";
    private final static String USER_NAME = "user name";

    @Test
    public void testThreadSession() {
        Assert.assertNull(SessionContext.getSession());
        CompletableFuture.runAsync(this::initThreadSession).join();
        Assert.assertNull(SessionContext.getSession());
    }

    @Test
    public void testRemoveSession() {
        this.initThreadSession();
        Assert.assertNotNull(SessionContext.getSession());
        SessionContext.removeSession();
        Assert.assertNull(SessionContext.getSession());
    }

    @Test
    public void testSessionClaim() {
        this.initThreadSession();
        Assert.assertTrue(SessionContext.hasClaim("key"));
        Assert.assertFalse(SessionContext.hasClaim("yyyy"));

        Assert.assertEquals("value", SessionContext.getClaim("key"));
    }

    @Test
    public void testSessionContext() {
        this.initThreadSession();

        Assert.assertEquals(COUNTRY, SessionContext.getCountry());
        Assert.assertEquals(LANGUAGE, SessionContext.getLanguage());
        Assert.assertEquals(TimeZone.getDefault(), SessionContext.getTimeZone());
        Assert.assertEquals(USER_ID, SessionContext.getUserId());
        Assert.assertEquals(TENANT_ID, SessionContext.getTenantId());
        Assert.assertEquals(ACCOUNT, SessionContext.getAccount());
        Assert.assertEquals(USER_NAME, SessionContext.getUserName());
    }

    private void initThreadSession() {
        EafcSession session = new EafcSession();
        session.setCountry(COUNTRY);
        session.setLanguage(LANGUAGE);
        session.setTimeZone(TIMEZONE);
        session.setUserId(USER_ID);
        session.setTenantId(TENANT_ID);
        session.setAccount(ACCOUNT);
        session.setUserName(USER_NAME);
        session.setClaim("key", "value");

        SessionContext.setSession(session);
    }
}
