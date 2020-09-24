package com.titanic.fork.domain.point;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface Calculator {

    int sumCalculate(List<Point> points);

    int monthlyPointCalculate(List<Point> monthlySavedPoints, Integer year, Integer month);
}