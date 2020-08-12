package com.zcloud.space.upms.api.vo;


import lombok.Data;

import java.time.LocalDate;

/**
 * @Description 用户基本信息表
 * @Author
 * @Date
 */
@Data
public class SysUserVO {
    /**
     * 自增长用户id
     */
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
     * 是否启用
     */
    private Byte isUse;
    /**
     * 是否删除
     */
    private Byte isDeleted;
    /**
     * 是否锁定
     */
    private Byte isLocked;

}
