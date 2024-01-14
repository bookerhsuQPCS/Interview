package com.interview.demo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CurrencyException extends RuntimeException {

    private static final long serialVersionUID = -4167522520586365967L;
    
	private String message;

    public CurrencyException(String message){
        this.message = message;
    }
}
