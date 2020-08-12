package com.zcloud.space.upms.api.vo;

import lombok.Data;

/**
 * @Description
 * @Author
 * @Date
 */
@Data
public class SysUserSecurityVO {
    private SysUserVO sysUserVO;
    private String[] permissions;
    private String[] roles;
}
