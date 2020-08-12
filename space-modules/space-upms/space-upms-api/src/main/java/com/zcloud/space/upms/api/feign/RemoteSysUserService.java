package com.zcloud.space.upms.api.feign;

import com.zcloud.space.common.core.constant.SecurityConstants;
import com.zcloud.space.common.core.constant.ServiceNameConstants;
import com.zcloud.space.common.core.util.R;
import com.zcloud.space.upms.api.vo.SysUserSecurityVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @Description
 * @Author
 * @Date
 */
@FeignClient(value = ServiceNameConstants.UMPS_SERVICE)
public interface RemoteSysUserService {
    /**
     * 查询用户
     *
     * @param username 用户名
     * @param from     调用标志
     * @return R
     */
    @GetMapping("/sysUser/getSecurity/{username}")
    R<SysUserSecurityVO> getSecurity(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM) String from);

}
