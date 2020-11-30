package com.anton.edith.core.annotation;

import com.anton.edith.core.enumeration.UserLevel;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Required {
    UserLevel level() default UserLevel.TOURIST;
}
