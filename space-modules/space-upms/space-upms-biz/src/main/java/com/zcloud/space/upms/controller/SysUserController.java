package com.zcloud.space.upms.controller;

import com.zcloud.space.common.core.util.R;
import com.zcloud.space.upms.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author
 * @Date
 */
@RestController
@RequestMapping("/sysUser")
@AllArgsConstructor
@Slf4j
@Api(value = "/sysUser", tags = "系统用户")
public class SysUserController {

    private ISysUserService sysUserService;
    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    @ApiOperation("根据用户名获取用户信息")
    @GetMapping("/getSecurity/{username}")
    public R getSecurity(@PathVariable String username) {
        return new R<>().success(sysUserService.getSecurity(username));
    }

    @ApiOperation("hello")
    @GetMapping("/hello")
    public R hello() {
        return new R<>().success("hello");
    }

}
