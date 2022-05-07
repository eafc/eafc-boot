package org.eafc.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import org.eafc.core.Assert;
import org.eafc.service.entity.IPO;
import org.eafc.service.mapper.AbstractMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.*;

/**
 * @author liuxx
 * @date 2022/5/7
 */
public abstract class AbstractService<M extends AbstractMapper<T>,T extends IPO<TKey>,TKey extends Serializable> implements IService<T,TKey> {

    @Autowired
    protected M mapper;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 根据主键查询PO
     *
     * @param id id
     * @return {@link T}
     */
    @Override
    public T getById(TKey id) {
        if (isEmptyKey(id)) {
            return null;
        }

        return mapper.selectById(id);
    }

    /**
     * 根据 Wrapper，查询一条记录
     * 结果集，如果是多个会抛出异常，随机取一条加上限制条件 wrapper.last("LIMIT 1")
     *
     * @param queryWrapper 查询条件
     * @return {@link T}
     */
    @Override
    public T getOne(Wrapper<T> queryWrapper) {
        return getOne(queryWrapper, false);
    }

    /**
     * 根据 Wrapper，查询一条记录
     *
     * @param queryWrapper 查询条件
     * @param throwEx      有多个 result 是否抛出异常
     * @return {@link T}
     */
    @Override
    public T getOne(Wrapper<T> queryWrapper, boolean throwEx){
        if(queryWrapper == null){
            return null;
        }
        if (throwEx) {
            return mapper.selectOne(queryWrapper);
        }
        List<T> list = mapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(list)) {
            int size = list.size();
            if (size > 1) {
                logger.warn(String.format("Warn: execute Method There are  %s results.", size));
            }
            return list.get(0);
        }
        return null;
    }

    /**
     * 根据主键集合查询PO列表
     *
     * @param ids 主键ID集合
     * @return {@link List<T>}
     */
    @Override
    public List<T> listByIds(Collection<TKey> ids) {
        List<T> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(ids)) {
            list = mapper.selectBatchIds(ids);
        }
        return list;
    }

    /**
     * 根据条件查询PO列表
     *
     * @param queryWrapper 查询条件
     * @return {@link List<T>}
     */
    @Override
    public List<T> list(Wrapper<T> queryWrapper) {
        return mapper.selectList(queryWrapper);
    }

    /**
     * 根据条件查询Map列表
     *
     * @param queryWrapper 查询条件
     * @return {@link List<Map>}
     */
    @Override
    public List<Map<String, Object>> listMaps(Wrapper<T> queryWrapper) {
        return mapper.selectMaps(queryWrapper);
    }

    /**
     * 根据条件查询总记录数
     *
     * @param queryWrapper 查询条件
     * @return 总数量
     */
    @Override
    public Long count(Wrapper<T> queryWrapper) {
        return mapper.selectCount(queryWrapper);
    }

    /**
     * 根据PO保存数据
     *
     * @param po {@link T}
     * @return 保存后的PO
     */
    @Override
    public T save(T po) {
        if (isEmptyKey(po.getId())) {
            this.insert(po);
        } else {
            this.update(po);
        }
        return po;
    }

    /**
     * 插入
     *
     * @param po {@link T}
     * @return 影响条数
     */
    @Override
    public int insert(T po) {
        Assert.notNull(po);

        return mapper.insert(po);
    }

    /**
     * 根据Id更新PO
     *
     * @param po {@link T}
     * @return 影响条数
     */
    @Override
    public int update(T po) {
        Assert.notNull(po);
        Assert.isFalse(this.isEmptyKey(po.getId()));

        return mapper.updateById(po);
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     * @return 影响条数
     */
    @Override
    public int delete(TKey id) {
        Assert.isFalse(this.isEmptyKey(id));

        return mapper.deleteById(id);
    }

    /**
     * 根据条件删除记录
     *
     * @param queryWrapper 查询条件
     * @return 影响条数
     */
    @Override
    public int delete(Wrapper<T> queryWrapper) {
        Assert.notNull(queryWrapper);

        return mapper.delete(queryWrapper);
    }

    /**
     * 根据ID集合删除记录
     *
     * @param ids 主键ID集合
     * @return 影响条数
     */
    @Override
    public int deleteByIds(Collection<TKey> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        return mapper.deleteBatchIds(ids);
    }

    /*
      以下的方法使用介绍:

      一. 名称介绍
      1. 方法名带有 query 的为对数据的查询操作, 方法名带有 update 的为对数据的修改操作
      2. 方法名带有 lambda 的为内部方法入参 column 支持函数式的

      二. 支持介绍
      1. 方法名带有 query 的支持以 {@link ChainQuery} 内部的方法名结尾进行数据查询操作
      2. 方法名带有 update 的支持以 {@link ChainUpdate} 内部的方法名为结尾进行数据修改操作

      三. 使用示例,只用不带 lambda 的方法各展示一个例子,其他类推
      1. 根据条件获取一条数据: `query().eq("column", value).one()`
      2. 根据条件删除一条数据: `update().eq("column", value).remove()`

     */

    /**
     * 链式查询 普通
     *
     * @return {@link QueryChainWrapper<T>}
     */
    @Override
    public QueryChainWrapper<T> query() {
        return ChainWrappers.queryChain(mapper);
    }

    /**
     * 链式查询 lambda 式
     * 注意：不支持 Kotlin
     *
     * @return {@link LambdaQueryChainWrapper<T>}
     */
    @Override
    public LambdaQueryChainWrapper<T> lambdaQuery() {
        return ChainWrappers.lambdaQueryChain(mapper);
    }

    /**
     * 链式更新 普通
     *
     * @return {@link UpdateChainWrapper<T>}
     */
    @Override
    public UpdateChainWrapper<T> update() {
        return ChainWrappers.updateChain(mapper);
    }

    /**
     * 链式更新 lambda 式
     * 注意：不支持 Kotlin
     *
     * @return {@link LambdaUpdateChainWrapper<T>}
     */
    @Override
    public LambdaUpdateChainWrapper<T> lambdaUpdate() {
        return ChainWrappers.lambdaUpdateChain(mapper);
    }

    /**
     * 主键是否为空
     * <p>
     * 子类需根据主键类型重写该方法
     * 比如：Long类型的主键判断大于0;String类型的主键判断不为空字符串
     *
     * @param id id
     * @return true or false
     */
    protected boolean isEmptyKey(TKey id) {
        return id == null;
    }
}
