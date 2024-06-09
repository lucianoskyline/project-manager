package com.project.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BusinessExceptionController {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity exception(MethodArgumentNotValidException ex){
        BusinessExceptionHandler businessExceptionHandler=new BusinessExceptionHandler(ex.getFieldError().getDefaultMessage());
        return ResponseEntity.badRequest().body(businessExceptionHandler);
    }

}
