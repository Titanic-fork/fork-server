package com.titanic.fork.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.NoResultException;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(AlreadyExistedException.class)
    public ResponseEntity<Void> alreadyExisted() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchAccountException.class)
    public ResponseEntity<Void> noSuchAccount() {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /*
     * Repository에서 해당 객체가 없을 때 발생하는 에러를 핸들링하는 메서
     */
    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Void> noResult() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
