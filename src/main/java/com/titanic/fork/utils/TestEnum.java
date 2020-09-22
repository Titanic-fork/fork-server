package com.titanic.fork.utils;

import lombok.Getter;

@Getter
public enum TestEnum {
    LOCALHOST("http://localhost:"), SERVICE_URL("http://15.165.66.150/api/"),
    JWT_TOKEN_EXAMPLE("eyJIUzI1NiI6IkhTMjU2IiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJBdXRob3JpemF0aW9uIjoiZ3Vzd25zMTY1M0BnbWFpbC5jb20iLCJzdWIiOiJndXN3bnMxNjUzQGdtYWlsLmNvbSIsImV4cCI6MTYwMTYzODI4OCwiaWF0IjoxNjAwNzc0Mjg4fQ.A6dxOdl0HnHqogXln8imccrZJ_WxVdYKbE9f728duXg"),
    ORIGIN("Origin"), ALL("*");

    private String value;

    TestEnum(String value) {
        this.value = value;
    }
}
