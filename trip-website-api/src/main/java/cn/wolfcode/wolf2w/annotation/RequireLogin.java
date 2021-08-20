package cn.wolfcode.wolf2w.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 登录拦截标记注解
 * 1>如果请求映射方法贴了这个注解表示该请求方法必须登录之后才可以访问
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireLogin {
}
