package com.anton.edith.core.annotation;

import com.anton.edith.core.enumeration.UserLevel;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Required(level = UserLevel.LOGIN)
public @interface LoginRequired {
}
