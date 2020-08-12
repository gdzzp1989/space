package com.zcloud.space.common.core.constant;

/**
 * @Description
 * @Author
 * @Date
 */
public class Constants {
    /**
     * header 中租户ID
     */
    public static final String TENANT_ID = "TENANT_ID";
    /**
     * 正常
     */
    public static final Byte STATUS_NORMAL = 1;
    /**
     * 删除
     */
    public static final Byte STATUS_DELETE = 0;
    /**
     * 锁定
     */
    public static final Byte STATUS_LOCK = 9;
    /**
     * 编码
     */
    public static final String UTF8 = "UTF-8";
    /**
     * 成功标记
     */
    public static final Integer SUCCESS = 1;
    /**
     * 失败标记
     */
    public static final Integer FAIL = 0;
    /**
     *  默认密码
     */
    public static final String DEFAULT_PASSWORD = "123456";
    /**
     * 标识：是
     */
    public static final Byte BYTE_YES = 1;
    /**
     * 标识：否
     */
    public static final Byte BYTE_NO = 0;
    /**
     * limit 1
     */
    public static final String LIMIT_1 = "LIMIT 1";
    /**
     * 验证码前缀
     */
    public static final String CODE_KEY = "CODE_KEY_";
    /**
     * 验证码有效期
     */
    public static final int CODE_TIME = 60;
    /**
     * 验证码长度
     */
    public static final int CODE_SIZE = 60;
    /**
     * 默认路由前缀
     */
    public static final String DEFAULT_GATEWAY_PREFIX = "";
}
