package com.titanic.fork.utils;

import lombok.Getter;

@Getter
public enum LocalEnum {
    LOCALHOST("http://localhost:"),
    JWT_TOKEN_GUSWNS1653("eyJIUzI1NiI6IkhTMjU2IiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJBdXRob3JpemF0aW9uIjoiZ3Vzd25zMTY1M0BnbWFpbC5jb20iLCJzdWIiOiJndXN3bnMxNjUzQGdtYWlsLmNvbSIsImV4cCI6MTYwMTYzODI4OCwiaWF0IjoxNjAwNzc0Mjg4fQ.A6dxOdl0HnHqogXln8imccrZJ_WxVdYKbE9f728duXg"),
    JWT_TOKEN_GUSWNS1654("eyJIUzI1NiI6IkhTMjU2IiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJBdXRob3JpemF0aW9uIjoiZ3Vzd25zMTY1NEBnbWFpbC5jb20iLCJzdWIiOiJndXN3bnMxNjU0QGdtYWlsLmNvbSIsImV4cCI6MTYwMTcyMzI2NCwiaWF0IjoxNjAwODU5MjY0fQ.nuzjS4_WZj-ICLrHZtn7c2qKdicGLE2dMmOvnkdu6A8"),
    JWT_TOKEN_GUSWNS1655("eyJIUzI1NiI6IkhTMjU2IiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJBdXRob3JpemF0aW9uIjoiZ3Vzd25zMTY1NUBnbWFpbC5jb20iLCJzdWIiOiJndXN3bnMxNjU1QGdtYWlsLmNvbSIsImV4cCI6MTYwMTcyMzQ5NCwiaWF0IjoxNjAwODU5NDk0fQ.Qj7wFePBD7IPqj6RQalNTN12_Tp15mWHEa1o0Dp6TGQ"),
    ORIGIN("Origin"), ALL("*");

    public String value;

    LocalEnum(String value) {
        this.value = value;
    }
}
