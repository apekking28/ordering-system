package com.apekking.exception;

public class NotNullOrEmptyException extends IllegalArgumentException{

    public NotNullOrEmptyException(String message){
        super(message);
    }
}
