package com.titanic.fork.web.login;

import lombok.Getter;

@Getter
public enum LoginEnum {
    USER_EMAIL("userEmail"),  HEADER_LOCATION("Location"), OPTIONS("OPTIONS"),
    HEADER_ACCEPT("Accept"), HEADER_MEDIA_TYPE("application/json"), AUTHORIZATION("Authorization"),
    SECRET_KEY("airbnbClone"), TYP("typ"), TYP_VALUE("JWT"), ALG("HS256"), TOKEN("token"),
    JWT_TOKEN_EXAMPLE("eyJIUzI1NiI6IkhTMjU2IiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJBdXRob3JpemF0aW9uIjoiZ3Vzd25zMTY1OSIsInN1YiI6Imd1c3duczE2NTkiLCJleHAiOjE2MDA2MDE1NzUsImlhdCI6MTU5OTczNzU3NX0.xVL5v-ZFxvR4F65HeKJgU1f68H_p18M7FjPWkXftDPA");

    private String value;

    LoginEnum(String value) {
        this.value = value;
    }
}
