package com.winsonone.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Winson on 2016/12/2 0002.
 * 校验结果模型
 */
public class ValidationResult {

    /**
     * 是否合法
     */
    private boolean isValid;

    /**
     * 非法提示信息
     */
    private List<String> messages;

    public ValidationResult() {
        messages = new ArrayList<>();
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

}
