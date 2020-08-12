package com.zcloud.space.common.data.tenant;

import com.zcloud.space.common.core.constant.Constants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description
 * @Author
 * @Date
 */
@Slf4j
public class FeignTenantInterceptor implements RequestInterceptor {
	@Override
	public void apply(RequestTemplate requestTemplate) {
		if (TenantContextHolder.getTenantId() == null) {
			log.error("TTL 中的 租户ID为空，feign拦截器 >> 增强失败");
			return;
		}
		requestTemplate.header(Constants.TENANT_ID, TenantContextHolder.getTenantId().toString());
	}
}
