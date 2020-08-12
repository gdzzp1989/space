package com.zcloud.space.upms.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description 路由配置表
 * @Author
 * @Date
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_route")
public class SysRoute extends Model<SysRoute> {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId
    private Integer id;
    /**
     * 路由前缀
     */
    private String gatewayPrefix;
    /**
     * 路由名称
     */
    private String routeName;
    /**
     * 路由ID
     */
    private String routeId;
    /**
     * 断言
     */
    private String predicates;
    /**
     * 过滤器
     */
    private String filters;
    /**
     * 路由uri
     */
    private String uri;
    /**
     * 排序
     */
    @TableField(value = "`order`")
    private Integer order;
    /**
     * 是否启用
     */
    @TableField(fill = FieldFill.INSERT)
    private Byte isUse;
    /**
     * 是否删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Byte isDeleted;
    /**
     * 机构id
     */
    private Long orgId;

    /**
     * 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
