package com.titanic.fork.exception;

public class NoSuchAccountException extends RuntimeException {

    public NoSuchAccountException() {
    }

    public NoSuchAccountException(String message) {
        super(message);
    }
}
