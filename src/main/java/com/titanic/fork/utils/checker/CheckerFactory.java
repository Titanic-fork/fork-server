package com.titanic.fork.utils.checker;

public interface CheckerFactory {

    DistanceChecker getChecker(String unit);

    static CheckerFactory getInstance() {
        return new DefaultCheckerFactory();
    }
}
