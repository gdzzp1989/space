package com.zcloud.space.common.security.util;


import com.zcloud.space.common.core.constant.RedisCacheConstants;
import com.zcloud.space.common.core.util.SpringContextHolder;
import com.zcloud.space.common.security.service.MyUser;
import com.zcloud.space.upms.api.vo.SysRoleVO;
import lombok.experimental.UtilityClass;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 安全工具类
 * @Author
 * @Date
 */
@UtilityClass
public class SecurityUtils {

    private static CacheManager cacheManager = ((CacheManager) SpringContextHolder.getBean("cacheManager"));

    /**
     * 获取Authentication
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     *
     * @param authentication
     * @return MyUser
     * <p>
     * 获取当前用户的全部信息 EnableSpaceResourceServer true
     * 获取当前用户的用户名 EnableSpaceResourceServer false
     */
    public MyUser getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof MyUser) {
            MyUser user = (MyUser) principal;
            String token = ((OAuth2AuthenticationDetails) (authentication).getDetails()).getTokenValue();
            user.setToken(token);
            return user;
        }
        return null;
    }

    /**
     * 获取token
     *
     * @param authentication
     * @return
     */
    public String getToken(Authentication authentication) {
        String token = ((OAuth2AuthenticationDetails) (authentication).getDetails()).getTokenValue();
        return token;
    }

    /**
     * 获取用户
     */
    public MyUser getUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return getUser(authentication);
    }

    /**
     * 获取当前登录人的token
     */
    public static String getToken() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return getToken(authentication);
    }

    /**
     * 获取用户角色信息
     *
     * @return 角色集合
     */
    public List<String> getRoles() {
        Authentication authentication = getAuthentication();
        String token = getToken(authentication);

        List<String> roleIds = new ArrayList<>();
        Cache cache = cacheManager.getCache(RedisCacheConstants.USER_ROLES);
        if (cache != null && cache.get(token) != null) {
            List<SysRoleVO> roleList = (List<SysRoleVO>) cache.get(token).get();
            roleIds = roleList.stream().map(SysRoleVO::getCode).collect(Collectors.toList());
        }

        return roleIds;
    }

}
