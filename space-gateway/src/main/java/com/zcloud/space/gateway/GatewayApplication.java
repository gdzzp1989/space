package com.zcloud.space.gateway;

import com.zcloud.space.common.gateway.annotation.EnableDynamicRoute;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @Description
 * @Author
 * @Date
 */
@EnableDynamicRoute
@SpringCloudApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
