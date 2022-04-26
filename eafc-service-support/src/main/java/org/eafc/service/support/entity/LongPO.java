package org.eafc.service.support.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author liuxx
 * @date 2022/4/26
 */
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
public abstract class LongPO extends LongPurePO implements ISoftDelete, IHasCreatedAt, IHasUpdatedAt {

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean delFlag;

    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;
}
