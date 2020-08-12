
package com.zcloud.space.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description 社交登录类型
 * @Author
 * @Date
 */
@Getter
@AllArgsConstructor
public enum LoginTypeEnum {
    /**
     * 账号密码登录
     */
    PWD("PWD", "账号密码登录"),

    /**
     * 验证码登录
     */
    SMS("SMS", "验证码登录"),

    /**
     * QQ登录
     */
    QQ("QQ", "QQ登录"),

    /**
     * 微信登录
     */
    WECHAT("WECHAT", "微信登录");


    /**
     * 类型
     */
    private final String type;
    /**
     * 描述
     */
    private final String description;
}
