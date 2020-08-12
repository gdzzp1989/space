package com.zcloud.space.upms.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 用户基本信息表
 * @Author
 * @Date
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class SysUser extends Model<SysUser> {
    private static final long serialVersionUID = 1L;

    /**
     * 自增长用户id
     */
    @TableId
    private Long id;
    /**
     * 用户名称-可登陆
     */
    private String userName;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 身份证号
     */
    private String idNumber;

    /**
     * 移动电话号码
     */
    private String mobile;
    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Byte isDeleted;
    /**
     * 是否锁定
     */
    private Byte isLocked;
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
