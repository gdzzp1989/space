package com.zcloud.space.upms.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zcloud.space.upms.api.vo.SysUserSecurityVO;
import com.zcloud.space.upms.api.vo.SysUserVO;
import com.zcloud.space.upms.entity.SysUser;

/**
 * @Description 用户基本信息表
 * @Author
 * @Date
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    SysUserSecurityVO getSecurity(String username);

}

