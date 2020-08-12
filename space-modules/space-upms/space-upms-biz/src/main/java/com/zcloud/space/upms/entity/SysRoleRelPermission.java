package com.zcloud.space.upms.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 角色权限关联表
 * @Author
 * @Date
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role_rel_permission")
public class SysRoleRelPermission extends Model<SysRoleRelPermission> {
    private static final long serialVersionUID = 1L;

    /**
     * 表 id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 角色表 id
     */
    private Integer roleId;
    /**
     * 权限表id
     */
    private Integer permissionId;

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
