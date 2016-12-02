package com.winsonone.annotation;

import java.lang.annotation.*;

/**
 * Created by Winson on 2016/12/2 0002.
 * 日期格式校验
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Date {

    /**
     * 属性名称
     * @return
     */
    String fieldName() default "日期格式";

    /**
     * 日期格式
     * @return
     */
    String pattern() default "yyyy-MM-dd HH:mm:ss";

}
