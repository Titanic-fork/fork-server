package com.titanic.fork.utils;

import lombok.Getter;

@Getter
public enum TestEnum {
    LOCALHOST("http://localhost:"), SERVICE_URL("http://15.165.66.150/api/");

    private String value;

    TestEnum(String value) {
        this.value = value;
    }
}
