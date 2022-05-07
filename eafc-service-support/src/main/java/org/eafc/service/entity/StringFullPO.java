package org.eafc.service.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author liuxx
 * @since 2022/4/26
 */
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
public abstract class StringFullPO extends StringPO implements IHasCreatedBy, IHasUpdatedBy {

    @TableField(fill = FieldFill.INSERT)
    private String createdBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;
}
