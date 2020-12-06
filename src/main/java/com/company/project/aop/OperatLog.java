package com.company.project.aop;

import java.lang.annotation.*;

/**
 * 日志记录自定义注解
 */

@Target({ ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperatLog {

    //操作功能
    String function() default "";
    //功能模块
    String module() default "";
    //操作详情
    String content() default "";

}
