package com.zcloud.space.upms.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 权限表
 * @Author
 * @Date
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_permission")
public class SysPermission extends Model<SysPermission> {
    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单权限标识
     */
    private String permission;
    /**
     * 前端URL
     */
    private String path;
    /**
     * 请求链接
     */
    private String url;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 父菜单ID
     */
    private Integer parentId;
    /**
     * 图标
     */
    private String icon;
    /**
     * VUE页面
     */
    private String component;
    /**
     * 排序值
     */
    private Integer sort;
    /**
     * 菜单类型 （0菜单 1按钮）
     */
    private String type;
    /**
     *
     */
    private String title;
    /**
     *
     */
    private Byte level;

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
