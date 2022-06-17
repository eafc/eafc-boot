package org.eafc.service.mapper.interceptor;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.eafc.core.session.SessionContext;

import java.util.Date;

/**
 * 审计字段自动填充 处理器
 *
 * @author liuxx
 * @date 2022/5/7
 */
public class AuditMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Date current = new Date();

        setFieldValByName("delFlag", false, metaObject);
        setFieldValByName("createdAt", current, metaObject);
        setFieldValByName("createdBy", SessionContext.getUserId(), metaObject);
        setFieldValByName("updatedAt", current, metaObject);
        setFieldValByName("updatedBy", SessionContext.getUserId(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updatedAt", new Date(), metaObject);
        setFieldValByName("updatedBy", SessionContext.getUserId(), metaObject);
    }
}
