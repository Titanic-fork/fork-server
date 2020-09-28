package com.titanic.fork.exception.account;

public class LoginAuthenticationFail extends RuntimeException {

    public LoginAuthenticationFail() {
    }

    public LoginAuthenticationFail(String message) {
        super(message);
    }

    public LoginAuthenticationFail(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAuthenticationFail(Throwable cause) {
        super(cause);
    }
}
