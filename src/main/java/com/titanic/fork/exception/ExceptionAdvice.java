package com.titanic.fork.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(AlreadyExistedException.class)
    public ResponseEntity<Void> alreadyExisted() {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchAccountException.class)
    public ResponseEntity<Void> noSuchAccount() {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
