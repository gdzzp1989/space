package com.zcloud.space.common.dynamic.gateway.configuration;

import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.config.PropertiesRouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 动态路由配置类
 * @Author
 * @Date
 */
@Configuration
@ComponentScan("com.zcloud.space.common.gateway")
public class DynamicRouteAutoConfiguration {
	/**
	 * 配置文件设置为空
	 * redis 加载为准
	 *
	 * @return
	 */
	@Bean
	public PropertiesRouteDefinitionLocator propertiesRouteDefinitionLocator() {
		return new PropertiesRouteDefinitionLocator(new GatewayProperties());
	}
}
