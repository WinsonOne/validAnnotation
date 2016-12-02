package com.winsonone.annotation;

import java.lang.annotation.*;

/**
 * Created by Winson on 2016/12/2 0002.
 * 最大长度
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MaxLength {

    /**
     * 属性名称
     * @return
     */
    String fieldName();

    /**
     * 最大长度
     * @return
     */
    int maxLength();

}
