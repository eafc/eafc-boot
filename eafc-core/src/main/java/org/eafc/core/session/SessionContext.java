package org.eafc.core.session;

import org.apache.commons.lang3.StringUtils;
import org.eafc.core.Constant;

import java.util.TimeZone;

/**
 * @author liuxx
 * @date 2022/3/15
 */
public class SessionContext {

    private static final ThreadLocal<EafcSession> LOCAL = ThreadLocal.withInitial(() -> null);

    private SessionContext() {
    }

    /**
     * 获取session信息
     *
     * @return session
     */
    public static EafcSession getSession() {
        return LOCAL.get();
    }

    /**
     * 设置线程 Session 信息
     *
     * @param session session
     */
    public static void setSession(EafcSession session) {
        LOCAL.set(session);
    }

    /**
     * 获取当前会话的国家信息
     *
     * @return country
     */
    public static String getCountry() {
        EafcSession session = getSession();
        return session == null ? Constant.EMPTY : session.getCountry();
    }

    /**
     * 获取当前会话的语言信息
     *
     * @return language
     */
    public static String getLanguage() {
        EafcSession session = getSession();
        return session == null ? Constant.EMPTY : session.getLanguage();
    }

    /**
     * 获取当前会话的时区信息
     *
     * @return timezone
     */
    public static TimeZone getTimeZone() {
        EafcSession session = getSession();
        return session == null ? TimeZone.getDefault() : session.getTimeZone();
    }

    /**
     * 获取当前登录用户userId
     *
     * @return userId
     */
    public static String getUserId() {
        EafcSession session = getSession();
        return session == null ? Constant.EMPTY : session.getUserId();
    }

    /**
     * 获取当前登录用户租户id
     *
     * @return tenantId
     */
    public static String getTenantId() {
        EafcSession session = getSession();
        return session == null ? Constant.EMPTY : session.getTenantId();
    }

    /**
     * 获取当前登录账户
     *
     * @return account
     */
    public static String getAccount() {
        EafcSession session = getSession();
        return session == null ? Constant.EMPTY : session.getAccount();
    }

    /**
     * 获取当前登录用户名
     *
     * @return userName
     */
    public static String getUserName() {
        EafcSession session = getSession();
        return session == null ? Constant.EMPTY : session.getUserName();
    }

    /**
     * 是否包含claim信息
     *
     * @param key Key
     * @return 是否包含
     */
    public static boolean hasClaim(String key) {
        if (StringUtils.isBlank(key)) {
            return false;
        }

        EafcSession session = getSession();
        return session != null && session.hasClaim(key);
    }

    /**
     * 获取claim信息
     *
     * @param key Key
     * @return claim
     */
    public static Object getClaim(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        EafcSession session = getSession();
        return session == null ? null : session.getClaim(key);
    }

    /**
     * 移除线程 Session 信息
     */
    public static void removeSession() {
        LOCAL.remove();
    }
}
