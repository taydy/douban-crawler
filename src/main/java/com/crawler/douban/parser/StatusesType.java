package com.crawler.douban.parser;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright with Taydy.
 *
 * @author taydy
 * @date 2019/2/20
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface StatusesType {

    /**
     * 用来表示解析器类型。
     */
    String[] value();

}
