package com.wych.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnums {

    GOODS_NOT_FOUND(200,"未查到货源");

    private int code;

    private String msg;



}
