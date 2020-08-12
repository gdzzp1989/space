package com.zcloud.space.upms.api.vo;

import lombok.Data;

/**
 * @Description
 * @Author
 * @Date
 */
@Data
public class SysRoleVO {

    private Integer id;
    /**
     * 角色code
     */
    private String code;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色类型 ：0超级管理员1学校管理员……
     */
    private Byte type;
    /**
     * 角色等级
     */
    private Byte level;
    /**
     * 学校或是教育集团 id：默认为 0通用角色，否则为机构专用角色
     */
    private Integer orgId;
    /**
     * 学校或是集团名称
     */
    private String orgName;
    /**
     * 备注
     */
    private String note;

}
