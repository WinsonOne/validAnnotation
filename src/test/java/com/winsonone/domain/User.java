package com.winsonone.domain;

import com.winsonone.annotation.*;

/**
 * Created by Winson on 2016/12/2 0002.
 * 用户模型
 */
public class User {

    @MinLength(fieldName = "用户名", minLength = 3)
    @MaxLength(fieldName = "用户名", maxLength = 10)
    @NotNull(fieldName = "用户名")
    private String userName;

    @Mobile
    @NotNull(fieldName = "手机号码")
    private String mobile;

    @Email
    private String email;

    @Date
    private String birthDay;

    @IDCard
    private String idCard;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
