package com.titanic.fork.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateSupplier {

    public LocalDateTime localDateTime(int year, int month) {
        return LocalDateTime.of(year,month, DateEnum.FIRST.getValue(),DateEnum.ZERO.getValue(),DateEnum.ZERO.getValue());
    }
}
