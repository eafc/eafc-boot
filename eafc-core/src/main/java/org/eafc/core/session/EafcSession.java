package org.eafc.core.session;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author liuxx
 * @date 2022/3/15
 */
@Data
public class EafcSession {

    /**
     * 国家
     */
    private String country;
    /**
     * 语言
     */
    private String language;
    /**
     * 时区
     */
    private TimeZone timeZone;
    /**
     * 租户id
     */
    private String tenantId;
    /**
     * user Id
     */
    private String userId;
    /**
     * 账户名
     */
    private String account;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 摘要信息集合
     */
    private Map<String, Object> claims;

    public EafcSession() {
        this.claims = new HashMap<>();
    }

    /**
     * 是否包含claim信息
     *
     * @param key Key
     * @return 是否包含
     */
    public boolean hasClaim(String key) {
        return StringUtils.isNotBlank(key) && this.claims.containsKey(key);
    }

    /**
     * 获取claim信息
     *
     * @param key Key
     * @return claim
     */
    public Object getClaim(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return this.claims.get(key);
    }

    /**
     * 设置claim信息
     *
     * @param key   Key
     * @param value Value
     */
    public void setClaim(String key, Object value) {
        if (StringUtils.isBlank(key)) {
            return;
        }
        this.claims.put(key, value);
    }
}
