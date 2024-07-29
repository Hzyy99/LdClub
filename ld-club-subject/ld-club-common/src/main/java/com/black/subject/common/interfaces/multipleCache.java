package com.black.subject.common.interfaces;
import com.black.subject.common.enums.CacheType;

import java.lang.annotation.*;

/**
 * 多路缓存注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface multipleCache {
    String cacheName();

    String key();//支持springEl表达式

    long l2TimeOut() default 5;

    CacheType type() default CacheType.FULL;
}
