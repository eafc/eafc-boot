package org.eafc.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import org.eafc.service.entity.IPO;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author liuxx
 * @date 2022/5/7
 */
public interface IService<T extends IPO<TKey>,TKey extends Serializable> {

    /**
     * 根据主键查询PO
     *
     * @param id id
     * @return {@link T}
     */
    T getById(TKey id);

    /**
     * 根据 Wrapper，查询一条记录
     * 结果集，如果是多个会抛出异常，随机取一条加上限制条件 wrapper.last("LIMIT 1")
     *
     * @param queryWrapper 查询条件
     * @return {@link T}
     */
    T getOne(Wrapper<T> queryWrapper);

    /**
     * 根据 Wrapper，查询一条记录
     *
     * @param queryWrapper 查询条件
     * @param throwEx      有多个 result 是否抛出异常
     * @return {@link T}
     */
    T getOne(Wrapper<T> queryWrapper, boolean throwEx);

    /**
     * 根据主键集合查询PO列表
     *
     * @param ids 主键ID集合
     * @return {@link List<T>}
     */
    List<T> listByIds(Collection<TKey> ids);

    /**
     * 根据条件查询PO列表
     *
     * @param queryWrapper 查询条件
     * @return {@link List<T>}
     */
    List<T> list(Wrapper<T> queryWrapper);

    /**
     * 根据条件查询Map列表
     *
     * @param queryWrapper 查询条件
     * @return {@link List<Map>}
     */
    List<Map<String, Object>> listMaps(Wrapper<T> queryWrapper);

    /**
     * 根据条件查询总记录数
     *
     * @param queryWrapper 查询条件
     * @return 总数量
     */
    Long count(Wrapper<T> queryWrapper);

    /**
     * 根据条件校验记录是否存在
     *
     * @param queryWrapper 查询条件
     * @return 是否存在
     */
    default boolean exists(Wrapper<T> queryWrapper) {
        Long count = this.count(queryWrapper);
        return null != count && count > 0;
    }

    /**
     * 根据PO保存数据
     *
     * @param po {@link T}
     * @return 保存后的PO
     */
    T save(T po);

    /**
     * 插入
     *
     * @param po {@link T}
     * @return 影响条数
     */
    int insert(T po);

    /**
     * 根据Id更新PO
     *
     * @param po {@link T}
     * @return 影响条数
     */
    int update(T po);

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     * @return 影响条数
     */
    int delete(TKey id);

    /**
     * 根据条件删除记录
     *
     * @param queryWrapper 查询条件
     * @return 影响条数
     */
    int delete(Wrapper<T> queryWrapper);

    /**
     * 根据ID集合删除记录
     *
     * @param ids 主键ID集合
     * @return 影响条数
     */
    int deleteByIds(Collection<TKey> ids);

    /**
     * 链式查询 普通
     *
     * @return {@link QueryChainWrapper<T>}
     */
    QueryChainWrapper<T> query();

    /**
     * 链式查询 lambda 式
     * 注意：不支持 Kotlin
     *
     * @return {@link LambdaQueryChainWrapper<T>}
     */
    LambdaQueryChainWrapper<T> lambdaQuery();

    /**
     * 链式更新 普通
     *
     * @return {@link UpdateChainWrapper<T>}
     */
    UpdateChainWrapper<T> update();

    /**
     * 链式更新 lambda 式
     * 注意：不支持 Kotlin
     *
     * @return {@link LambdaUpdateChainWrapper<T>}
     */
    LambdaUpdateChainWrapper<T> lambdaUpdate();
}
