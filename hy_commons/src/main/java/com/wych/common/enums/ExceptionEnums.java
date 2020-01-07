package com.wych.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnums {

    REGISTER_FAIL(200,"注册失败"),
    LOGIN_FAIL(200,"登陆失败");

    private int code;
    private String msg;



}
