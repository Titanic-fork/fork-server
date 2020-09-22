package com.titanic.fork.exception.account;

public class LoginAuthenticationFail extends RuntimeException {

    public LoginAuthenticationFail() {
    }

    public LoginAuthenticationFail(String message) {
        super(message);
    }
}
