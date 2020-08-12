package com.zcloud.space.common.security.annotation;

import com.zcloud.space.common.security.component.ResourceServerAutoConfiguration;
import com.zcloud.space.common.security.component.SecurityBeanDefinitionRegister;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 * @Description 资源服务器注解
 * @Author
 * @Date
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ResourceServerAutoConfiguration.class, SecurityBeanDefinitionRegister.class})
public @interface EnableSpaceResourceServer {

    /**
     * false：上下文获取用户名
     * false：上下文获取用户名
     * true： 上下文获取全部用户信息
     *
     * @return
     */
    boolean details() default false;
}
