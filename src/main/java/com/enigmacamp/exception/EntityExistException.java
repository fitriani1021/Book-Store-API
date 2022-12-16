package com.enigmacamp.exception;

public class EntityExistException extends RuntimeException {
    public EntityExistException() {
        super("Data is exists");
    }
    
    public EntityExistException(String message) {
        super(message);
    }
}
