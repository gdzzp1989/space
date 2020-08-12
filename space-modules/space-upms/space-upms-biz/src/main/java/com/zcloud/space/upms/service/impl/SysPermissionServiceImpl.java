package com.zcloud.space.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcloud.space.upms.entity.SysPermission;
import com.zcloud.space.upms.mapper.ISysPermissionMapper;
import com.zcloud.space.upms.service.ISysPermissionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 权限表
 * @Author
 * @Date
 */
@Service
@AllArgsConstructor
public class SysPermissionServiceImpl extends ServiceImpl<ISysPermissionMapper, SysPermission> implements ISysPermissionService {

    private final ISysPermissionMapper sysPermissionMapper;

    /**
     * 获取用户的权限list
     * @param userId
     * @return
     */
    @Override
    public List<SysPermission> getPermission(Long userId) {
        return sysPermissionMapper.getPermission(userId);
    }
}
