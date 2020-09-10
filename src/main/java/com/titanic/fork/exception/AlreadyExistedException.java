package com.titanic.fork.exception;

public class AlreadyExistedException extends RuntimeException {
    public AlreadyExistedException() {
    }

    public AlreadyExistedException(String message) {
        super(message);
    }
}
