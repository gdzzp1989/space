package com.zcloud.space.upms;

import com.zcloud.space.common.security.annotation.EnableSpaceFeignClients;
import com.zcloud.space.common.security.annotation.EnableSpaceResourceServer;
import com.zcloud.space.common.swagger.annotation.EnableSpaceSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @Description
 * @Author
 * @Date
 */
@EnableSpaceSwagger2
@SpringCloudApplication
@EnableSpaceFeignClients
@EnableSpaceResourceServer(details = true)
public class UpmsBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpmsBizApplication.class, args);
    }

}
