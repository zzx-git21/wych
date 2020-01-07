package com.wych.common.exception;


import com.wych.common.enums.ExceptionEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HyException extends RuntimeException {
        private ExceptionEnums exceptionEnum;
}
