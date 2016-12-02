package com.winsonone.util;

import com.winsonone.annotation.*;
import com.winsonone.domain.CommonRegEx;
import com.winsonone.domain.ValidationResult;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Winson on 2016/12/2 0002.
 * 自定义注解校验工具类
 * 对外提供对象校验方法
 */
public class ValidationUtil {

    /**
     * 验证对象的所有自定义注解
     * @param object 待校验对象
     * @return 校验结果
     */
    public static ValidationResult validateObject(Object object){
        ValidationResult validationResult = new ValidationResult();
        // 获取对象的字节码对象
        Class objectClass = object.getClass();
        // 获取字节码对象所有的属性
        Field[] fields = objectClass.getDeclaredFields();
        for(Field field : fields){
            // 遍历所有的属性，获取属性上的注解对象
            // 设置field为private时设置可以访问权限
            field.setAccessible(true);
            // 验证属性上的注解
            List<String> messages = validateField(field, object, validationResult.getMessages());
            // 重新对field设置权限
            field.setAccessible(false);
        }
        if (validationResult.getMessages().size() > 0){
            validationResult.setValid(false);
        }else {
            validationResult.setValid(true);
        }
        return validationResult;
    }

    /**
     * 验证属性上的注解
     * @param field 待校验对象的属性
     * @param object 待校验对象
     * @param messages 非法提示信息容器
     * @return 非法提示信息
     */
    private static List<String> validateField(Field field, Object object, List<String> messages) {
        // 日期注解校验
        messages = validateDate(field, object, messages);
        // 邮箱地址校验
        messages = validateEmail(field, object, messages);
        // 身份证号校验
        messages = validateIDCard(field, object, messages);
        // 长度校验
        messages = validateLength(field, object, messages);
        // 最大长度校验
        messages = validateMaxLength(field, object, messages);
        // 最小长度校验
        messages = validateMinLength(field, object, messages);
        // 手机号码校验
        messages = validateMobile(field, object, messages);
        // 非NULL校验
        messages = validateNotNull(field, object, messages);
        // 正则表达式校验
        messages = validateRegEx(field, object, messages);
        return messages;
    }

    /**
     * 正则表达式校验
     * @param field
     * @param object
     * @param messages
     * @return
     */
    private static List<String> validateRegEx(Field field, Object object, List<String> messages) {
        if (field.isAnnotationPresent(RegEx.class)){
            try {
                Object value = field.get(object);
                if (value instanceof String){
                    String fieldValue = (String) value;
                    RegEx regExAnnotation = field.getAnnotation(RegEx.class);
                    if (fieldValue != null && !Pattern.matches(regExAnnotation.regex(),fieldValue)){
                        messages.add(regExAnnotation.fieldName() + "不合法");
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return messages;
    }

    /**
     * 非NULL校验
     * @param field
     * @param object
     * @param messages
     * @return
     */
    private static List<String> validateNotNull(Field field, Object object, List<String> messages) {
        if (field.isAnnotationPresent(NotNull.class)){
            try {
                Object value = field.get(object);
                NotNull notNullAnnotation = field.getAnnotation(NotNull.class);
                if (value == null){
                    messages.add(notNullAnnotation.fieldName() + "不允许为null");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return messages;
    }

    /**
     * 手机号码校验
     * @param field
     * @param object
     * @param messages
     * @return
     */
    private static List<String> validateMobile(Field field, Object object, List<String> messages) {
        if (field.isAnnotationPresent(Mobile.class)){
            try {
                Object value = field.get(object);
                if (value instanceof String){
                    Mobile mobileAnnotation = field.getAnnotation(Mobile.class);
                    String fieldValue = (String) value;
                    if (fieldValue != null && !Pattern.matches(CommonRegEx.REGEX_MOBILE,fieldValue)){
                        messages.add(mobileAnnotation.fieldName() + "不合法");
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return messages;
    }

    /**
     * 最小长度校验
     * @param field
     * @param object
     * @param messages
     * @return
     */
    private static List<String> validateMinLength(Field field, Object object, List<String> messages) {
        if (field.isAnnotationPresent(MinLength.class)){
            // 字符串最小长度校验
            try {
                Object value = field.get(object);
                if (value instanceof String) {
                    MinLength minLengthAnnotation = field.getAnnotation(MinLength.class);
                    String fieldValue = (String) value;
                    if (fieldValue == null || fieldValue.length() < minLengthAnnotation.minLength()) {
                        messages.add(minLengthAnnotation.fieldName() + "不能小于" + minLengthAnnotation.minLength() + "位");
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return messages;
    }

    /**
     * 最大长度校验
     * @param field
     * @param object
     * @param messages
     * @return
     */
    private static List<String> validateMaxLength(Field field, Object object, List<String> messages) {
        if (field.isAnnotationPresent(MaxLength.class)){
            // 字符串最大长度校验
            try {
                Object value = field.get(object);
                if (value instanceof String) {
                    MaxLength maxLengthAnnotation = field.getAnnotation(MaxLength.class);
                    String fieldValue = (String) value;
                    if (fieldValue != null && fieldValue.length() > maxLengthAnnotation.maxLength()) {
                        messages.add(maxLengthAnnotation.fieldName() + "不能大于" + maxLengthAnnotation.maxLength() + "位");
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return messages;
    }

    /**
     * 长度校验
     * @param field
     * @param object
     * @param messages
     * @return
     */
    private static List<String> validateLength(Field field, Object object, List<String> messages) {
        if (field.isAnnotationPresent(Length.class)){
            try {
                Object value = field.get(object);
                if (value instanceof String){
                    String fieldValue = (String) value;
                    Length lengthAnnotation = field.getAnnotation(Length.class);
                    if (fieldValue == null || fieldValue.length() != lengthAnnotation.length()){
                        messages.add(lengthAnnotation.fieldName() + "必须为" + lengthAnnotation.length() + "位");
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return messages;
    }

    /**
     * 身份证号校验
     * @param field
     * @param object
     * @param messages
     * @return
     */
    private static List<String> validateIDCard(Field field, Object object, List<String> messages) {
        if (field.isAnnotationPresent(IDCard.class)){
            // 身份证号码校验
            try {
                Object value = field.get(object);
                if (value instanceof String) {
                    IDCard idCardAnnotation = field.getAnnotation(IDCard.class);
                    String fieldValue = (String) value;
                    if (fieldValue != null && !Pattern.matches(CommonRegEx.REGEX_ID_CARD, fieldValue)) {
                        messages.add(idCardAnnotation.fieldName() + "不合法");
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return messages;
    }

    /**
     * 邮箱地址校验
     * @param field
     * @param object
     * @param messages
     * @return
     */
    private static List<String> validateEmail(Field field, Object object, List<String> messages) {
        if (field.isAnnotationPresent(Email.class)){
            // 邮箱地址校验
            try {
                Object value = field.get(object);
                if (value instanceof String){
                    Email emailAnnotation = field.getAnnotation(Email.class);
                    String fieldValue = (String) value;
                    if (fieldValue != null && !Pattern.matches(CommonRegEx.REGEX_EMAIL,fieldValue)){
                        messages.add(emailAnnotation.fieldName() + "不合法");
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return messages;
    }

    /**
     * 日期注解校验
     * @param field
     * @param object
     * @param messages
     * @return
     */
    private static List<String> validateDate(Field field, Object object, List<String> messages) {
        if (field.isAnnotationPresent(Date.class)){
            // 日期格式校验
            try {
                Object value = field.get(object);
                Date dateAnnotation = field.getAnnotation(Date.class);
                if (value instanceof String){
                    String fieldValue = (String) value;
                    SimpleDateFormat sdf = new SimpleDateFormat(dateAnnotation.pattern());
                    try {
                        sdf.parse(fieldValue);
                    } catch (ParseException e) {
                        messages.add(dateAnnotation.fieldName() + "不合法");
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return messages;
    }
}
