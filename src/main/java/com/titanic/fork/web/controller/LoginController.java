package com.titanic.fork.web.controller;

import com.titanic.fork.service.LoginService;
import com.titanic.fork.web.dto.request.account.ValidateNameAndPasswordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;

@RestController
@RequiredArgsConstructor
@RequestMapping("account")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("find")
    public ResponseEntity<Void> validateNameAndPassword(@RequestBody ValidateNameAndPasswordDto
                                                                    validateNameAndPasswordDto) {
        return loginService.validateNameAndPassword(validateNameAndPasswordDto);
    }

    /*
     * AccountRepository에서 해당 계정이 없을 때 발생하는 에러를 핸들링하는 메서드
     */
    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Void> noResultAccount() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
