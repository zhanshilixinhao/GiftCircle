package com.chouchongkeji.goexplore.common;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ImgUrl {

    String value() default "";
}