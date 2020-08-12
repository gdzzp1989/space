package com.zcloud.space.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcloud.space.upms.entity.SysUserRelRole;
import com.zcloud.space.upms.mapper.ISysUserRelRoleMapper;
import com.zcloud.space.upms.service.ISysUserRelRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Description 用户 角色关联表
 * @Author
 * @Date
 */
@Service
@AllArgsConstructor
public class SysUserRelRoleServiceImpl extends ServiceImpl<ISysUserRelRoleMapper, SysUserRelRole> implements ISysUserRelRoleService {

    private final ISysUserRelRoleMapper iSysUserRelRoleMapper;


}
