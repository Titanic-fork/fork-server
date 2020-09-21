package com.titanic.fork.utils;

import lombok.Getter;

@Getter
public enum TestEnum {
    LOCALHOST("http://localhost:"), SERVICE_URL("http://15.165.66.150/api/"),
    JWT_TOKEN_EXAMPLE("eyJIUzI1NiI6IkhTMjU2IiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJBdXRob3JpemF0aW9uIjoiZ3Vzd25zMTY1OSIsInN1YiI6Imd1c3duczE2NTkiLCJleHAiOjE2MDA2MDE1NzUsImlhdCI6MTU5OTczNzU3NX0.xVL5v-ZFxvR4F65HeKJgU1f68H_p18M7FjPWkXftDPA");

    private String value;

    TestEnum(String value) {
        this.value = value;
    }
}
