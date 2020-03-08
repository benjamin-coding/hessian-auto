package com.minwait.hessian.auto;

import java.lang.annotation.*;

/**
 * HessianApi
 * Hessian 自动接口装配 注解
 *
 * @author by xunmi
 * @version 1.0
 * @date 2020/3/4 11:48
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HessianApi {
    /**
     * 实现类的bean名称，如果为空则使用Impl
     *
     * @return
     */
    String value() default "";
}
