package com.cr1stal423.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LoansAlreadyExistsException extends RuntimeException {
    public LoansAlreadyExistsException(String message){
        super(message);
    }
}
