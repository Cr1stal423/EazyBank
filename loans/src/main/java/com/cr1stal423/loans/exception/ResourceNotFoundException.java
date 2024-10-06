package com.cr1stal423.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resourceName, String fieldName, String filedCValue) {
        super(String.format("%s not found with given data %s : %s",resourceName,fieldName,filedCValue));
    }
}
