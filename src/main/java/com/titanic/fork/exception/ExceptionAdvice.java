package com.titanic.fork.exception;

import com.titanic.fork.exception.account.LoginAuthenticationFail;
import com.titanic.fork.exception.account.NoSuchAccountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    /*
     * 회원가입 시 해당 이메일이 존재하는 경우 : 202, Accepted
     */
    @ExceptionHandler(AlreadyExistedException.class)
    public ResponseEntity<Void> alreadyExisted() {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /*
     * Account을 조회할 때 조회결과가 없을 때 : 204, No Content
     */
    @ExceptionHandler(NoSuchAccountException.class)
    public ResponseEntity<Void> noSuchAccount() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
     * 로그인 시 비밀번호 다르면 : 403, Forbidden
     */
    @ExceptionHandler(LoginAuthenticationFail.class)
    public ResponseEntity<Void> passwordWrong() {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
