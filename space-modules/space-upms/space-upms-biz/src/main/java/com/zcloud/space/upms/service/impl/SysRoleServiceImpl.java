package com.zcloud.space.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcloud.space.upms.entity.SysRole;
import com.zcloud.space.upms.mapper.ISysRoleMapper;
import com.zcloud.space.upms.service.ISysRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author
 * @Date
 */
@Service
@AllArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<ISysRoleMapper, SysRole> implements ISysRoleService {

    private final ISysRoleMapper iSysRoleMapper;

}
