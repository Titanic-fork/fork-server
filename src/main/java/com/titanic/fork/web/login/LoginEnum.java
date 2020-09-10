package com.titanic.fork.web.login;

import lombok.Getter;

@Getter
public enum LoginEnum {
    USER_EMAIL("userEmail"),  HEADER_LOCATION("Location"), OPTIONS("OPTIONS"),
    HEADER_ACCEPT("Accept"), HEADER_MEDIA_TYPE("application/json"), AUTHORIZATION("Authorization"),
    SECRET_KEY("airbnbClone"), TYP("typ"), TYP_VALUE("JWT"), ALG("HS256"), TOKEN("token"),
    JWT_TOKEN_EXAMPLE("eyJIUzI1NiI6IkhTMjU2IiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJBdXRob3JpemF0aW9uIjoiZ3Vzd25zMTY1OUBnbWFpbC5jb20iLCJzdWIiOiJndXN3bnMxNjU5QGdtYWlsLmNvbSIsImV4cCI6MTU5OTk2ODExMywiaWF0IjoxNTk5MTA0MTEzfQ.s3WGbolbFqjOXGZ45PBsNKZvIixkfLcrDuASpStOwfw");

    private String value;

    LoginEnum(String value) {
        this.value = value;
    }
}
