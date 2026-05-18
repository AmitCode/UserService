package com.theMainApplication.exceptions.SuppliersOprException;

public class InvalidStatusException extends RuntimeException{
    String message;
    public InvalidStatusException(String message){
        super(message);
        this.message = message;
    }
}
