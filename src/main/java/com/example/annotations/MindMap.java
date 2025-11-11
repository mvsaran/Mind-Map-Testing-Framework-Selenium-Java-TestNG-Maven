package com.example.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MindMap {
    String node();
    String priority() default "P1";
    String file() default "docs/SauceDemo_MindMap.xmind";
    String ci() default "";
}
