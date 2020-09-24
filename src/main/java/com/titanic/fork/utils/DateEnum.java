package com.titanic.fork.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum DateEnum {
    FIRST(1), ZERO(0);

    DateEnum(int value) {
        this.value = value;
    }

    private int value;
}
