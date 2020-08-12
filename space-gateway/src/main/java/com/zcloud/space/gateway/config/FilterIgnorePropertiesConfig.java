package com.zcloud.space.gateway.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 网关不校验终端配置
 * @Author
 * @Date
 */
@Data
@Configuration
@RefreshScope
@ConditionalOnExpression("!'${ignore}'.isEmpty()")
@ConfigurationProperties(prefix = "ignore")
public class FilterIgnorePropertiesConfig {
	private String gatewayPrefix;
	private List<String> clients = new ArrayList<>();
	private List<String> swaggerProviders=new ArrayList<>();
}
