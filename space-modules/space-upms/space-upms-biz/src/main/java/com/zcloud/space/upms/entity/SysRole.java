package com.zcloud.space.upms.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 角色表
 * @Author
 * @Date
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class SysRole extends Model<SysRole> {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 角色code
     */
    private String code;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色类型 ：0超级管理员1学校管理员……
     */
    private Byte type;
    /**
     * 角色等级
     */
    private Byte level;
    /**
     * 学校或是教育集团 id：默认为 0通用角色，否则为机构专用角色
     */
    private Integer orgId;
    /**
     * 学校或是集团名称
     */
    private String orgName;
    /**
     * 备注
     */
    private String note;

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


    /**
     * 角色的使用人数
     */
    @TableField(exist = false)
    private Integer userCount;
}
