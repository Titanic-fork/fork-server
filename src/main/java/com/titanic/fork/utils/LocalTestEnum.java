package com.titanic.fork.utils;

import lombok.Getter;

public enum LocalTestEnum {
    JWT_TOKEN_LOCAL_TEST_1("eyJIUzI1NiI6IkhTMjU2IiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJBdXRob3JpemF0aW9uIjoibG9jYWxUZXN0MUBnbWFpbC5jb20iLCJzdWIiOiJsb2NhbFRlc3QxQGdtYWlsLmNvbSIsImV4cCI6MTYzNDM4OTY1NCwiaWF0IjoxNjAyODUzNjU0fQ.SMkzjytUUymfstwmNYc8oC2OOSG0zZpbBCec7CsgNDo"),
    JWT_TOKEN_LOCAL_TEST_2(""),
    JWT_TOKEN_LOCAL_TEST_3("");

    public String token;

    LocalTestEnum(String token) {
        this.token = token;
    }
}
