package com.titanic.fork.exception.accountGoal;

public class NoSuchAccountGoalException extends RuntimeException {

    public NoSuchAccountGoalException() {
    }

    public NoSuchAccountGoalException(String message) {
        super(message);
    }
}
