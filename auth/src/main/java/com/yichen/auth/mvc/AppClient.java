package com.yichen.auth.mvc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * springMVC自定义的数据绑定
 *
 * @author yichenshanren
 * @date 2017/12/14
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)

public @interface AppClient {

    String value() default "client";

}
