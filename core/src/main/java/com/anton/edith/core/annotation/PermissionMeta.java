package com.anton.edith.core.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionMeta {

    String value();

    @Deprecated
    String permission() default "";

    String module() default "";

    boolean mount() default true;

}
