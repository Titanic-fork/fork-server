package com.titanic.fork.utils.checker;

public enum DistanceUnit {
    METER("meter"), KILOMETER("kilometer"), MILE("mile");

    public String unit;

    DistanceUnit(String unit) {
        this.unit = unit;
    }
}
