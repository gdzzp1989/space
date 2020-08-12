
package com.zcloud.space.common.core.constant;

/**
 * @Description
 * @Author
 * @Date
 */
public class SecurityConstants {

    /**
     * 租户ID 字段
     */
    public static final String DETAILS_TENANT_ID = "tenant_id";
    /**
     * 内部
     */
    public static final String FROM_IN = "Y";
    /**
     * 标志
     */
    public static final String FROM = "from";
    /**
     * 用户ID字段
     */
    public static final String USER_ID = "user_id";
    /**
     * 客户端模式password
     */
    public static final String OAUTH_PASSWORD = "password";
    /**
     * 客户端模式implicit
     */
    public static final String OAUTH_IMPLICIT = "implicit";
    /**
     * 客户端模式client_credentials
     */
    public static final String OAUTH_CLIENT_CREDENTIALS = "client_credentials";
    /**
     * 客户端模式authorization_code
     */
    public static final String OAUTH_AUTHORIZATION_CODE = "authorization_code";
    /**
     * {bcrypt} 加密的特征码
     */
    public static final String BCRYPT = "{bcrypt}";
    /**
     * sys_oauth_clients 表的字段，不包括client_id、client_secret
     */
    public static final String CLIENT_FIELDS = "client_id, CONCAT('{noop}',client_secret) as client_secret, resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";
    /**
     * JdbcClientDetailsService 查询语句
     */
    public static final String BASE_FIND_STATEMENT = "select " + CLIENT_FIELDS
            + " from sys_oauth_clients";
    /**
     * 默认的查询语句
     */
    public static final String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";
    /**
     * 按条件client_id 查询
     */
    public static final String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " where client_id = ?";
    /**
     * 刷新
     */
    public static final String REFRESH_TOKEN = "refresh_token";
    /**
     * 角色前缀
     */
    public static final String ROLE = "ROLE_";
    /**
     * OAUTH URL
     */
    public static final String OAUTH_TOKEN_URL = "/oauth/token";
    /**
     * 资源服务器默认bean名称
     */
    public static final String RESOURCE_SERVER_CONFIGURER = "resourceServerConfigurerAdapter";












}
