package com.titanic.fork.utils;

import lombok.Getter;

public enum LocalTestEnum {
    JWT_TOKEN_LOCAL_TEST_1("eyJIUzI1NiI6IkhTMjU2IiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJBdXRob3JpemF0aW9uIjoibG9jYWxUZXN0MUBnbWFpbC5jb20iLCJzdWIiOiJsb2NhbFRlc3QxQGdtYWlsLmNvbSIsImV4cCI6MTYzNDAyMTQ5MCwiaWF0IjoxNjAyNDg1NDkwfQ.BtG14qTXYERmOmLIu8BcjxfmCGiSn5tOgQZPizdKczc"),
    JWT_TOKEN_LOCAL_TEST_2("eyJIUzI1NiI6IkhTMjU2IiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJBdXRob3JpemF0aW9uIjoibG9jYWxUZXN0MkBnbWFpbC5jb20iLCJzdWIiOiJsb2NhbFRlc3QyQGdtYWlsLmNvbSIsImV4cCI6MTYzNDAyMTU0MiwiaWF0IjoxNjAyNDg1NTQyfQ.2YN3tm4o_Do71NebCnrcQY3EHug60Nrzt8qEve1lv8A"),
    JWT_TOKEN_LOCAL_TEST_3("");

    public String token;

    LocalTestEnum(String token) {
        this.token = token;
    }
}
