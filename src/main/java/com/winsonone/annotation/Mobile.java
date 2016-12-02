package com.winsonone.annotation;

import java.lang.annotation.*;

/**
 * Created by Winson on 2016/12/2 0002.
 * 手机号码校验
 */
@Target(value = {ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Mobile {

    /**
     * 属性名称
     * @return
     */
    String fieldName() default "手机号码";

}
