package com.titanic.fork.web.login;

import lombok.Getter;

@Getter
public enum LoginEnum {
    USER_EMAIL("userEmail"),  HEADER_LOCATION("Location"), OPTIONS("OPTIONS"),
    HEADER_ACCEPT("Accept"), HEADER_MEDIA_TYPE("application/json"), AUTHORIZATION("Authorization"),
    SECRET_KEY("airbnbClone"), TYP("typ"), TYP_VALUE("JWT"), ALG("HS256"), TOKEN("token");

    public String value;

    LoginEnum(String value) {
        this.value = value;
    }
}
