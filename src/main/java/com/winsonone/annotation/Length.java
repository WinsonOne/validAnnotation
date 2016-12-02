package com.winsonone.annotation;

import java.lang.annotation.*;

/**
 * Created by Winson on 2016/12/2 0002.
 * 长度注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Length {

    /**
     * 属性名称
     * @return
     */
    String fieldName();

    /**
     * 字段长度
     * @return
     */
    int length();

}
