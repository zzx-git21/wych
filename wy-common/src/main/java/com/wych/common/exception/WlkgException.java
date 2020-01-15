package com.wych.common.exception;


import com.wych.common.enums.ExceptionEnums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 飞鸟
 * @create 2019-12-04 10:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WlkgException extends RuntimeException {
    private ExceptionEnums exceptionEnums;
}
