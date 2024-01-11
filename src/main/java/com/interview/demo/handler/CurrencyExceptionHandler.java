package com.interview.demo.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerInterceptor;

import com.interview.demo.exception.CurrencyException;

@ControllerAdvice
public class CurrencyExceptionHandler implements HandlerInterceptor {

    @ExceptionHandler(CurrencyException.class)
    public Object exceptionHandler(CurrencyException e) {
        String message = e.getMessage();
        String msg = "get error: CurrencyException: " + message;

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
    }
}
