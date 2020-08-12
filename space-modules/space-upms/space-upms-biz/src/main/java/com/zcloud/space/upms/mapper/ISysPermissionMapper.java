package com.zcloud.space.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zcloud.space.upms.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description 权限表
 * @Author
 * @Date
 */
@Mapper
public interface ISysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 获取用户的权限信息
     * @param userId
     * @return
     */
    List<SysPermission> getPermission(Long userId);
}
