package com.winsonone.annotation;

import java.lang.annotation.*;

/**
 * Created by Winson on 2016/12/2 0002.
 * 正则表达式注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RegEx {

    /**
     * 属性名称
     * @return
     */
    String fieldName();

    /**
     * 正则字符串
     * @return
     */
    String regex();

}
