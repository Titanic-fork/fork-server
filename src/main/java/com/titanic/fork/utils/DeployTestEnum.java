package com.titanic.fork.utils;

import lombok.Getter;

@Getter
public enum DeployTestEnum {
    SERVICE_URL("http://15.165.66.150/api/"),
    JWT_TOKEN_GUSWNS1653("eyJIUzI1NiI6IkhTMjU2IiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJBdXRob3JpemF0aW9uIjoiZ3Vzd25zMTY1M0BnbWFpbC5jb20iLCJzdWIiOiJndXN3bnMxNjUzQGdtYWlsLmNvbSIsImV4cCI6MTYwMTg5NzYxNywiaWF0IjoxNjAxMDMzNjE3fQ.nfrrASV5ltnTCmffrXshuyNDrWo6pAcggtvzMk1_M9o");

    private String value;

    DeployTestEnum(String value) {
        this.value = value;
    }
}
