package com.zcloud.space.auth;

import com.zcloud.space.common.security.annotation.EnableSpaceFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@EnableSpaceFeignClients
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
