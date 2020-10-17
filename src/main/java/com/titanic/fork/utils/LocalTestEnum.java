package com.titanic.fork.utils;

public enum LocalTestEnum {
    JWT_TOKEN_LOCAL_TEST_1("eyJIUzI1NiI6IkhTMjU2IiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJBdXRob3JpemF0aW9uIjoibG9jYWxUZXN0MUBnbWFpbC5jb20iLCJzdWIiOiJsb2NhbFRlc3QxQGdtYWlsLmNvbSIsImV4cCI6MTYzNDQ2NzY4NywiaWF0IjoxNjAyOTMxNjg3fQ.3uc360Hz17UxWrJw3hN4qFEsStDiRpUtKJsFE9TN9CU"),
    JWT_TOKEN_LOCAL_TEST_2(""),
    JWT_TOKEN_LOCAL_TEST_3("");

    public String token;

    LocalTestEnum(String token) {
        this.token = token;
    }
}
