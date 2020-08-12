package com.zcloud.space.upms.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 用户角色关联表
 * @Author
 * @Date
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user_rel_role")
public class SysUserRelRole extends Model<SysUserRelRole> {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Byte isDeleted;
    /**
     * 创建用户id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createUserId;
    /**
     * 创建用户名称
     */
    @TableField(fill = FieldFill.INSERT)
    private String createUserName;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新用户id
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long updateUserId;
    /**
     * 更新用户名称
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updateUserName;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
