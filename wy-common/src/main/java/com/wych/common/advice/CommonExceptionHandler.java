package com.wych.common.advice;


import com.wych.common.dto.ExceptionResult;
import com.wych.common.enums.ExceptionEnums;
import com.wych.common.exception.WychException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResult> handlerException(WychException e){

        ExceptionEnums enums = e.getExceptionEnum ();
        return ResponseEntity.status (HttpStatus.BAD_REQUEST).body (new ExceptionResult (enums));
    }

}
