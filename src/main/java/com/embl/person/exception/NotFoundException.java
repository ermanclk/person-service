package com.embl.person.exception;

public class NotFoundException  extends RuntimeException {
    public NotFoundException(String message){
        super(message);
    }
}