package com.pphh.demo.common;

/**
 * @author 帅小伙呀
 * @date 2024/7/17 00:20
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE}) // 指定注解可以应用于方法和类
@Retention(RetentionPolicy.RUNTIME) // 指定注解在运行时保留
public @interface AgentAnnotation {
    // 定义注解的属性
    String value() default "default value";
}