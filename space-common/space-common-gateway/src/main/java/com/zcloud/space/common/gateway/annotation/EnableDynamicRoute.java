package com.zcloud.space.common.gateway.annotation;

import com.zcloud.space.common.dynamic.gateway.configuration.DynamicRouteAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description 开启 动态路由
 * @Author
 * @Date
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(DynamicRouteAutoConfiguration.class)
public @interface EnableDynamicRoute {
}
