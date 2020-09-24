package com.titanic.fork.domain.point;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PointCalculator implements Calculator {

    @Override
    public int sumCalculate(List<Point> points) {
        return points.stream()
                .mapToInt(Point::getAmount)
                .sum();
    }

    @Override
    public int monthlyPointCalculate(List<Point> monthlySavedPoints, Integer year, Integer month) {
        return monthlySavedPoints.stream()
                .filter(point -> point.isPeriod(year,month))
                .mapToInt(Point::getAmount)
                .sum();
    }
}
