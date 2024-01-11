package com.interview.demo.exception;

import lombok.Data;

@Data
public class CurrencyException extends RuntimeException {

    private String message;

    public CurrencyException(String message){
        this.message = message;
    }
}
