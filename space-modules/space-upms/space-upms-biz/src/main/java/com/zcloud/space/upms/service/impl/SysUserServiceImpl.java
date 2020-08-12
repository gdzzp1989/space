package com.zcloud.space.upms.service.impl;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcloud.space.common.core.util.MapperUtils;
import com.zcloud.space.upms.api.vo.SysUserSecurityVO;
import com.zcloud.space.upms.api.vo.SysUserVO;
import com.zcloud.space.upms.entity.SysUser;
import com.zcloud.space.upms.mapper.ISysUserMapper;
import com.zcloud.space.upms.service.ISysUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Description 用户基本信息表
 * @Author
 * @Date
 */
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<ISysUserMapper, SysUser> implements ISysUserService {

    private final ISysUserMapper sysUserMapper;

    @Override
    public SysUserSecurityVO getSecurity(String username) {
        SysUser user = this.getOne(Wrappers.<SysUser>query()
                .lambda().eq(SysUser::getUserName, username).or().
                        eq(SysUser::getMobile, username));
        if (user == null) {
            return null;
        }
        SysUserVO sysUserVO = MapperUtils.INSTANCE.map(SysUserVO.class,user);
        SysUserSecurityVO VO = new SysUserSecurityVO();
        VO.setSysUserVO(sysUserVO);
        return VO;
    }
}
