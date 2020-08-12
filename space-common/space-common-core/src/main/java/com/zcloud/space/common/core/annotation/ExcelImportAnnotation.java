package com.zcloud.space.common.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description Excel表格导入注解
 * @Author
 * @Date
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelImportAnnotation {

    // 属性所在列的序号（列数）
    int order() default 100;

    // 属性对应的set方法
    String method() default "";

    // 属性对应Excel中的标题
    String columnTitle() default "";

    // 列的个数
    int length() default 20;
}
