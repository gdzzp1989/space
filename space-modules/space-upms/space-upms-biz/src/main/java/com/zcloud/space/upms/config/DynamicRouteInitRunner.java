package com.zcloud.space.upms.config;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.zcloud.space.common.core.constant.RedisCacheConstants;
import com.zcloud.space.common.gateway.support.DynamicRouteInitEvent;
import com.zcloud.space.upms.service.ISysRouteService;
import com.zcloud.space.common.dynamic.gateway.vo.RouteDefinitionVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.config.PropertiesRouteDefinitionLocator;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.scheduling.annotation.Async;

import java.net.URI;

/**
 * @Description 容器启动后保存配置文件里面的路由信息到Redis
 * @Author
 * @Date
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class DynamicRouteInitRunner {
	private final RedisTemplate redisTemplate;
	private final ISysRouteService routeService;

	@Async
	@Order
	@EventListener({WebServerInitializedEvent.class, DynamicRouteInitEvent.class})
	public void initRoute() {
		Boolean result = redisTemplate.delete(RedisCacheConstants.GATEWAY_ROUTE_KEY);
		log.info("初始化网关路由 {} ", result);

		routeService.routes().forEach(route -> {
			RouteDefinitionVO vo = new RouteDefinitionVO();
			vo.setGatewayPrefix(route.getGatewayPrefix());
			vo.setRouteName(route.getRouteName());
			vo.setId(route.getRouteId());
			vo.setUri(URI.create(route.getUri()));
			vo.setOrder(route.getOrder());

			JSONArray filterObj = JSONUtil.parseArray(route.getFilters());
			vo.setFilters(filterObj.toList(FilterDefinition.class));
			JSONArray predicateObj = JSONUtil.parseArray(route.getPredicates());
			vo.setPredicates(predicateObj.toList(PredicateDefinition.class));

			log.info("加载路由ID：{},{}", route.getRouteId(), vo);
			redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RouteDefinitionVO.class));
			redisTemplate.opsForHash().put(RedisCacheConstants.GATEWAY_ROUTE_KEY, route.getRouteId(), vo);
		});
		log.debug("初始化网关路由结束 ");
	}

	/**
	 * 配置文件设置为空redis 加载的为准
	 *
	 * @return
	 */
	@Bean
	public PropertiesRouteDefinitionLocator propertiesRouteDefinitionLocator() {
		return new PropertiesRouteDefinitionLocator(new GatewayProperties());
	}
}
