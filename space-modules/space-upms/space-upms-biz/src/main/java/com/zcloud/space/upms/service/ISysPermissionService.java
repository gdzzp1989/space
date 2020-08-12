package com.zcloud.space.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcloud.space.upms.entity.SysPermission;

import java.util.List;

/**
 * @Description 权限表
 * @Author
 * @Date
 */
public interface ISysPermissionService extends IService<SysPermission> {
    /**
     * 获取用户的权限list
     * @param userId
     * @return
     */
    List<SysPermission> getPermission(Long userId);
}


