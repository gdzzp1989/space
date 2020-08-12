package com.zcloud.space.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zcloud.space.upms.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 角色表
 * @Author
 * @Date
 */
@Mapper
public interface ISysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 获取当前登录人相关的角色
     * @param userId
     * @return
     */
    List<SysRole> getCurrentList(@Param("userId") Long userId);
}
