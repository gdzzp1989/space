package com.zcloud.space.common.data.tenant;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description feign 租户信息拦截
 * @Author
 * @Date
 */
@Configuration
public class FeignTenantConfiguration {
	@Bean
	public RequestInterceptor YmFeignTenantInterceptor() {
		return new FeignTenantInterceptor();
	}
}
