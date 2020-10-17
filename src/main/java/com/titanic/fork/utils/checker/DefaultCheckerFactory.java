package com.titanic.fork.utils.checker;

public class DefaultCheckerFactory implements CheckerFactory {
    @Override
    public DistanceChecker getChecker(String unit) {
        if (unit.equals(DistanceUnit.METER.unit)) {
            return new MeterDistanceChecker();
        }
        return new KilometerDistanceChecker();

    }
}
