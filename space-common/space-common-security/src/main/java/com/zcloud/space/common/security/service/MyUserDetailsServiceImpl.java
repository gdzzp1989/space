package com.zcloud.space.common.security.service;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.zcloud.space.common.core.constant.Constants;
import com.zcloud.space.common.core.constant.RedisCacheConstants;
import com.zcloud.space.common.core.constant.SecurityConstants;
import com.zcloud.space.common.core.util.R;
import com.zcloud.space.upms.api.feign.RemoteSysUserService;
import com.zcloud.space.upms.api.vo.SysUserSecurityVO;
import com.zcloud.space.upms.api.vo.SysUserVO;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description 用户详细信息
 * @Author
 * @Date
 */
@Slf4j
@Service
@AllArgsConstructor
public class MyUserDetailsServiceImpl implements MyUserDetailsService {

    private final RemoteSysUserService remoteUserService;
    private final CacheManager cacheManager;

    /**
     * 用户密码登录
     *
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String username) {
        Cache cache = cacheManager.getCache(RedisCacheConstants.USER_DETAILS);
        if (cache != null && cache.get(username) != null) {
            MyUser user = (MyUser) cache.get(username).get();
            return user;
        }
        R<SysUserSecurityVO> result = remoteUserService.getSecurity(username, SecurityConstants.FROM_IN);
        UserDetails userDetails = getUserDetails(result);
        cache.put(userDetails.getUsername(), userDetails);
        return userDetails;
    }


    /**
     * 构建userdetails
     *
     * @param result 用户信息
     * @return
     */
    private UserDetails getUserDetails(R<SysUserSecurityVO> result) {
        if (result == null || result.getData() == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        SysUserSecurityVO info = result.getData();
        Set<String> dbAuthsSet = new HashSet<>();
        if (ArrayUtil.isNotEmpty(info.getRoles())) {
            // 获取角色
            Arrays.stream(info.getRoles()).forEach(roleId -> dbAuthsSet.add(SecurityConstants.ROLE + roleId));
            // 获取资源
            dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));
        }
        Collection<? extends GrantedAuthority> authorities
                = AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
        SysUserVO user = info.getSysUserVO();
        boolean enabled = Constants.STATUS_NORMAL.equals(user.getIsLocked());
        // 构造security用户
        if(StrUtil.isBlank(user.getUserName())){
            user.setUserName(IdWorker.get32UUID());
        }
        return new MyUser(user.getId(), user.getUserName(), SecurityConstants.BCRYPT + user.getPassword(), enabled,
                true, true, !Constants.STATUS_LOCK.equals(user.getIsLocked()), authorities);
    }

}
