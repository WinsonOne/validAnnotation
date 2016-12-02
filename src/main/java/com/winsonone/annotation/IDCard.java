package com.winsonone.annotation;

import java.lang.annotation.*;

/**
 * Created by Winson on 2016/12/2 0002.
 * 身份证号校验
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface IDCard {

    /**
     * 属性名称
     * @return
     */
    String fieldName() default "身份证号";

}
