package com.zcloud.space.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcloud.space.upms.entity.SysRoleRelPermission;
import com.zcloud.space.upms.mapper.ISysRoleRelPermissionMapper;
import com.zcloud.space.upms.service.ISysRoleRelPermissionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Description 角色权限关联表
 * @Author
 * @Date
 */
@Service
@AllArgsConstructor
public class SysRoleRelPermissionServiceImpl extends ServiceImpl<ISysRoleRelPermissionMapper, SysRoleRelPermission> implements ISysRoleRelPermissionService {

    private final ISysRoleRelPermissionMapper iSysRoleRelPermissionMapper;


}
