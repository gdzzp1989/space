package com.zcloud.space.common.swagger.annotation;


import com.zcloud.space.common.swagger.SwaggerAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description 开启 swagger
 * @Author
 * @Date
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SwaggerAutoConfiguration.class})
public @interface EnableSpaceSwagger2 {
}
