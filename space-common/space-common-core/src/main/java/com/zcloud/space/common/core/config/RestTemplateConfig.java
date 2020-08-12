package com.zcloud.space.common.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Description RestTemplate
 * @Author
 * @Date
 */
@Configuration
public class RestTemplateConfig {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
