package com.titanic.fork.exception;

public class AlreadyExistedException extends RuntimeException {
    public AlreadyExistedException() {
    }

    public AlreadyExistedException(String message) {
        super(message);
    }

    public AlreadyExistedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistedException(Throwable cause) {
        super(cause);
    }
}
