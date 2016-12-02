package com.winsonone.annotation;

import java.lang.annotation.*;

/**
 * Created by Winson on 2016/12/2 0002.
 * 最小长度
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MinLength {

    /**
     * 属性名称
     * @return
     */
    String fieldName();

    /**
     * 最小长度
     * @return
     */
    int minLength();

}
