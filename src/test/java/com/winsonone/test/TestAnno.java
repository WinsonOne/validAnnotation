package com.winsonone.test;

import com.winsonone.domain.User;
import com.winsonone.domain.ValidationResult;
import com.winsonone.util.ValidationUtil;

/**
 * Created by Administrator on 2016/12/2 0002.
 */
public class TestAnno {

    public static void main(String[] args) {
        User user = new User();
        user.setUserName("用户");
        user.setMobile("12345678911");
        user.setEmail("1234");
        user.setIdCard("37498732423");
        user.setBirthDay("1990-02-20");
        ValidationResult validationResult = ValidationUtil.validateObject(user);
        if (validationResult.isValid()){
            System.out.println("校验通过");
        }else {
            for (String message : validationResult.getMessages()){
                System.out.println(message);
            }
        }
    }
}
