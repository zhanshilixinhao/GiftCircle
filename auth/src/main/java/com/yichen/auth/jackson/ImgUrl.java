package com.yichen.auth.jackson;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ImgUrl {

    String value() default "";

}
